package com.apuope.apuope_re.services;

import com.apuope.apuope_re.dto.QuizSummaryData;
import com.apuope.apuope_re.dto.ResponseData;
import com.apuope.apuope_re.jooq.tables.records.UsersRecord;
import com.apuope.apuope_re.repositories.UserRepository;
import org.jooq.DSLContext;

import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class PrintService {
    private final DSLContext dslContext;
    private final JWTService jwtService;
    private final QuizService quizService;
    private final UserRepository userRepository;

    public PrintService(DSLContext dslContext, JWTService jwtService, QuizService quizService,
            UserRepository userRepository){
        this.dslContext = dslContext;
        this.jwtService = jwtService;
        this.quizService = quizService;
        this.userRepository = userRepository;

    }

    public byte[] generatePdfAsBytes(String htmlContent) {
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            PdfRendererBuilder builder = new PdfRendererBuilder();
            builder.useFastMode();
            builder.withHtmlContent(htmlContent, null);
            builder.toStream(outputStream);
            builder.run();

            return outputStream.toByteArray();
        } catch (Exception e) {
            throw new RuntimeException("Error generating PDF", e);
        }
    }

    public ResponseData<Object> generatePdf(String token, Integer quizId){
        try {
            String userEmail = jwtService.extractEmail(token);
            Optional<UsersRecord> userOpt = userRepository.findVerifiedUserByEmail(userEmail, dslContext);

            if (userOpt.isPresent()) {
                QuizSummaryData quizSummaryData = quizService.fetchQuizSummaryData(quizId);
                Configuration cfg = new Configuration(Configuration.VERSION_2_3_32);
                cfg.setClassForTemplateLoading(this.getClass(), "/templates");

                Template template = cfg.getTemplate("pdfTemplate.ftl");
                StringWriter writer = new StringWriter();
                Map<String, Object> data = new HashMap<>();

                data.put("username", userOpt.get().getUsername());
                data.put("email", userOpt.get().getEmail());
                data.put("lectureId", quizSummaryData.getQuizData().getLectureId());
                data.put("quizId", quizSummaryData.getQuizId());
                data.put("quizData", quizSummaryData.getQuizData());
                data.put("quizResultData", quizSummaryData.getQuizResultData());
                template.process(data, writer);

                String htmlContent =  writer.toString();
                byte[] pdfBytes = generatePdfAsBytes(htmlContent);

                return new ResponseData<>(true, pdfBytes);
            }
            throw new Exception("User not found");
        } catch (Exception e){
            System.out.println(e);
            return new ResponseData<>(false, "PDF generation failed.");
        }
    }
}
