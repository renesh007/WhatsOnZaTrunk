package com.example.reneshn.whatsonza;

import java.util.List;

/**
 * Created by renesh on 2016-11-09.
 */

public class IdResponseDTO {

    @com.google.gson.annotations.SerializedName("data")
    private List<Data> data;
    @com.google.gson.annotations.SerializedName("paging")
    private Paging paging;

    public List<Data> getData() {
        return data;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }

    public Paging getPaging() {
        return paging;
    }

    public void setPaging(Paging paging) {
        this.paging = paging;
    }

    public static class Data {
        @com.google.gson.annotations.SerializedName("id")
        private String id;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
    }

    public static class Cursors {
        @com.google.gson.annotations.SerializedName("before")
        private String before;
        @com.google.gson.annotations.SerializedName("after")
        private String after;

        public String getBefore() {
            return before;
        }

        public void setBefore(String before) {
            this.before = before;
        }

        public String getAfter() {
            return after;
        }

        public void setAfter(String after) {
            this.after = after;
        }
    }

    public static class Paging {
        @com.google.gson.annotations.SerializedName("cursors")
        private Cursors cursors;

        public Cursors getCursors() {
            return cursors;
        }

        public void setCursors(Cursors cursors) {
            this.cursors = cursors;
        }
    }
}
