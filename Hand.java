import java.util.*;
//import java.io.*;

public class Hand implements Comparable<Hand> {
    ArrayList<Card> hand = new ArrayList<>();
    Card bestCard;
    int worth = 0;
    
    public Hand(ArrayList<Card> hand) {
        this.hand = hand;
        Collections.sort(hand);
        Collections.reverse(hand);
        this.bestCard = hand.get(0);

        if(this.isRoyalFlush()) {
            worth = 9;
        }
        else if(this.isStraightFlush()) {
            worth = 8;
        }
        else if(this.is4Kind()) {
            worth = 7;
        }
        else if(this.isFullHouse()) {
            worth = 6;
        }
        else if(this.isFlush()) {
            worth = 5;
        }
        else if(this.isStraight()) {
            worth = 4;
        }
        else if(this.is3Kind()) {
            worth = 3;
        }
        else if(this.isTwoPair()) {
            worth = 2;
        }
        else if(this.isOnePair()) {
            worth = 1;
        }

    }

    boolean isRoyalFlush() {
        if(this.isFlush() && this.isStraight() && this.bestCard.rank == 14) {
            return true;
        }
        else {
            return false;
        }
    }

    boolean isStraightFlush() {
        return (this.isStraight() && this.isFlush());
    }

    boolean is4Kind() {
        for(int i = 0; i < 5; i++) {
            if(hand.get(i).rank == hand.get(i + 1).rank 
            && hand.get(i + 1).rank == hand.get(i + 2).rank
            && hand.get(i + 2).rank == hand.get(i + 3).rank) {
                bestCard = hand.get(i);
                return true;
            }
        }

        return false;
    }

    boolean isFullHouse() {
        if(this.isTwoPair() && this.is3Kind()){
            return true;
        }
        return false;
    }
    
    boolean isFlush() {
        for(int i = 0; i < 2; i++) {
            if(hand.get(i).suit == hand.get(i + 1).suit
            && hand.get(i + 1).suit == hand.get(i + 2).suit
            && hand.get(i + 2).suit == hand.get(i + 3).suit
            && hand.get(i + 3).suit == hand.get(i + 4).suit) {
                bestCard = hand.get(i);
                return true;
            }
        }

        return false;
    }

    boolean isStraight() {
        for(int i = 0; i < 2; i++) {
            if(hand.get(i).rank - hand.get(i + 1).rank == 1
            && hand.get(i + 1).rank - hand.get(i + 2).rank == 1
            && hand.get(i + 2).rank - hand.get(i + 3).rank == 1
            && hand.get(i + 3).rank - hand.get(i + 4).rank == 1) {
                bestCard = hand.get(i);
                return true;
            }
        }

        for(int i = 0; i < 3; i++) {
            if(hand.get(i).rank - hand.get(i + 1).rank == 1
            && hand.get(i + 1).rank - hand.get(i + 2).rank == 1
            && hand.get(i + 2).rank - hand.get(i + 3).rank == 1
            && hand.get(i + 3).rank == 2
            && hand.get(0).rank == 14) {
                bestCard = hand.get(i);
                return true;
            }
        }

        return false;
    }
    
    boolean is3Kind() {
        for(int i = 0; i < 5; i++) {
            if(hand.get(i).rank == hand.get(i + 1).rank && hand.get(i + 1).rank == hand.get(i + 2).rank) {
                bestCard = hand.get(i);
                return true;
            }
        }

        return false;
    }

    boolean isTwoPair() {
        int c = 0;
        for(int i = 6; i >= 0; i--) {
            if(hand.get(i).rank == hand.get(i + 1).rank) {
                bestCard = hand.get(i);
                c++;
                i--;
            }
        }
        if(c == 2) {
            return true;
        }
        else {
            return false;
        }
    }

    boolean isOnePair() {
        for(int i = 0; i < 6; i++) {
            if(hand.get(i).rank == hand.get(i + 1).rank) {
                bestCard = hand.get(i);
                return true;
            }
        }
        return false;
    }

    @Override
    public int compareTo(Hand h) {
        if(this.worth == h.worth) {
            return this.bestCard.rank - h.bestCard.rank;
        }
        else {
            return this.worth - h.worth;
        }
    }
}