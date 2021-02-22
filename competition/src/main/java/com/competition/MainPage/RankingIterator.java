package com.competition.MainPage;

public interface RankingIterator {
    boolean hasNext();

    RankingModel getNext();

    void reset();
}