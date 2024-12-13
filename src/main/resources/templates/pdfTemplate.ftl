<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <style>
        body {
            font-family: 'Roboto', sans-serif;
        }
        p {
            font-size: 14px;
        }
        table {
            border-collapse: separate;
            border-spacing: 0;
        }
        .logo {
            width: 205px;
            height: 30px;
        }
        .correct-answer {
            border: 1px solid green;
            border-radius: 5px;
            display: inline-box;
            font-size: 14px;
            padding: 5px;
        }
        .wrong-answer {
            border: 1px solid red;
            border-radius: 5px;
            display: inline-box;
            font-size: 14px;
            padding: 5px;
        }
        .option {
            display: inline-box;
            font-size: 14px;
            padding: 5px;
        }
        .table-container {
            background-color: #BFDBFE;
            font-size: 24px;
            margin-bottom: 1em;
            padding: 15px;
            width: 100%;
        }
        .header-container {
            border-bottom: 2px solid #DEDEDE;
            margin-bottom: 1.2em;
        }
        .item-left {
            text-align: left;
        }
        .item-right {
            text-align: right;
        }
        .grid-container-result {
            display: grid;
            gap: 2em;
        }
        .grid-item-result {
            border-bottom: 2px solid #DEDEDE;
            margin-bottom: 1.2em;
        }
        .question {
            font-size: 15px;
            font-weight: bold;
            padding: 5px;
        }
        .correct-icon {
            width: 15px;
            height: 15px;
        }
        .incorrect-icon {
            width: 15px;
            height: 15px;
            margin-left: 5px;
        }
    </style>
</head>
<body>
    <div class="header-container">
        <table class="table-container">
            <tr>
                <td class="item-left">
                    <img src="file:///app/images/logo.png" class="logo"/>
                </td>
                <td>Lecture ${lectureId}</td>
                <td class="item-right">
                    ${quizResultData.score} / ${quizResultData.maxScore}
                </td>
            </tr>
        </table>
        <div>
            <p>Username: ${username}</p>
            <p>Email: ${email}</p>
        </div>
    </div>
    <div clas="grid-container-result">
        <#list quizData.questionDataList as question>
            <#assign answer = quizResultData.quizAnswerDataList?filter(answer -> answer.questionNumber == question.questionNumber)?first>
            <div class="grid-item-result">
                <p class="question">${question.questionNumber}. ${question.question}</p>

                <#if answer?exists && answer.answer == question.correctOption && question.correctOption == "option_a">
                    <p>
                        <span class="correct-answer">a. ${question.optionA}</span>
                        <img src="file:///app/images/correct.png" class="correct-icon"/>
                    </p>
                <#elseif answer?exists && answer.answer == "option_a" && question.correctOption != "option_a">
                    <p>
                        <span class="wrong-answer">a. ${question.optionA}</span>
                        <img src="file:///app/images/incorrect.png" class="incorrect-icon"/>
                    </p>
                <#elseif answer?exists && answer.answer != question.correctOption && question.correctOption == "option_a">
                    <p>
                        <span class="option">a. ${question.optionA}</span>
                        <img src="file:///app/images/correct.png" class="correct-icon"/>
                    </p>
                <#else>
                    <p class="option">a. ${question.optionA}</p>
                </#if>

                <#if answer?exists && answer.answer == question.correctOption && question.correctOption == "option_b">
                    <p>
                        <span class="correct-answer">b. ${question.optionB}</span>
                        <img src="file:///app/images/correct.png" class="correct-icon"/>
                    </p>
                <#elseif answer?exists && answer.answer == "option_b" && question.correctOption != "option_b">
                    <p>
                        <span class="wrong-answer">b. ${question.optionB}</span>
                        <img src="file:///app/images/incorrect.png" class="incorrect-icon"/>
                    </p>
                <#elseif answer?exists && answer.answer != question.correctOption && question.correctOption == "option_b">
                    <p>
                        <span class="option">b. ${question.optionB}</span>
                        <img src="file:///app/images/correct.png" class="correct-icon"/>
                    </p>
                <#else>
                    <p class="option">b. ${question.optionB}</p>
                </#if>

                <#if answer?exists && answer.answer == question.correctOption && question.correctOption == "option_c">
                    <p>
                        <span class="correct-answer">c. ${question.optionC}</span>
                        <img src="file:///app/images/correct.png" class="correct-icon"/>
                    </p>
                <#elseif answer?exists && answer.answer == "option_c" && question.correctOption != "option_c">
                    <p>
                        <span class="wrong-answer">c. ${question.optionC}</span>
                        <img src="file:///app/images/incorrect.png" class="incorrect-icon"/>
                    </p>
                <#elseif answer?exists && answer.answer != question.correctOption && question.correctOption == "option_c">
                    <p>
                        <span class="option">c. ${question.optionC}</span>
                        <img src="file:///app/images/correct.png" class="correct-icon"/>
                    </p>
                <#else>
                    <p class="option">c. ${question.optionC}</p>
                  </#if>
            </div>
        </#list>
    </div>
</body>
</html>