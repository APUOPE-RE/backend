package com.apuope.apuope_re.utils;

import java.util.List;

public class Constants {
    public enum MessageSource {
        USER(1),
        LLM(2);

        private final int value;

        MessageSource(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    public enum LectureChapters {
        LECTURE_2(2, List.of(1, 2, 3)),
        LECTURE_3(3, List.of(5, 6)),
        LECTURE_4(4, List.of(1, 8, 9, 14)),
        LECTURE_5(5, List.of(7)),
        LECTURE_6(6, List.of(11)),
        LECTURE_7(7, List.of(16)),
        LECTURE_8(8, List.of(10, 17)),
        LECTURE_9(9, List.of(20, 21, 22, 23, 24, 25, 27, 28)),
        LECTURE_10(10, List.of(29));

        private final int lectureId;
        private final List<Integer> chapterIds;

        LectureChapters(int lectureId, List<Integer> chapterIds) {
            this.lectureId = lectureId;
            this.chapterIds = chapterIds;
        }

        public int getLectureId() {
            return lectureId;
        }

        public List<Integer> getChapterIds() {
            return chapterIds;
        }

        public static List<Integer> getChaptersByLectureId(int lectureId) {
            for (LectureChapters lecture : LectureChapters.values()) {
                if (lecture.getLectureId() == lectureId) {
                    return lecture.getChapterIds();
                }
            }
            return List.of();
        }
    }
}
