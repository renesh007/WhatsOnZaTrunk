package com.example.reneshn.whatsonza;

/**
 * Created by ReneshN on 2016/11/08.
 */

public class Stats {
    private int attending,declined,maybe,noReply;

    public Stats(int attending, int declined, int maybe, int noReply) {
        this.attending = attending;
        this.declined = declined;
        this.maybe = maybe;
        this.noReply = noReply;
    }

    public Stats() {
        this.attending = 0;
        this.declined = 0;
        this.maybe = 0;
        this.noReply = 0;
    }

    public int getAttending() {
        return attending;
    }

    public void setAttending(int attending) {
        this.attending = attending;
    }

    public int getDeclined() {
        return declined;
    }

    public void setDeclined(int declined) {
        this.declined = declined;
    }

    public int getMaybe() {
        return maybe;
    }

    public void setMaybe(int maybe) {
        this.maybe = maybe;
    }

    public int getNoReply() {
        return noReply;
    }

    public void setNoReply(int noReply) {
        this.noReply = noReply;
    }
}
