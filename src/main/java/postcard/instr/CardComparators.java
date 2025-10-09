package postcard.instr;

import postcard.model.OldCard;
import java.util.Comparator;


public class CardComparators {

  
    public static Comparator<OldCard> byYear() {
        return Comparator.comparingInt(OldCard::getYear);
    }

    public static Comparator<OldCard> byCountry() {
        return Comparator.comparing(OldCard::getCountry);
    }


    public static Comparator<OldCard> byId() {
        return Comparator.comparing(OldCard::getId);
    }}