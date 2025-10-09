package postcard;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import postcard.instr.CardComparators;
import postcard.model.OldCard;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CardComparatorsTest {

    private List<OldCard> cards;

    @BeforeEach
    void setUp() {
        cards = new ArrayList<>();
        
        OldCard cardUkraine = new OldCard();
        cardUkraine.setCountry("Ukraine");
        cardUkraine.setYear(1985);
        
        OldCard cardPoland = new OldCard();
        cardPoland.setCountry("Poland");
        cardPoland.setYear(1992);

        OldCard cardGermany = new OldCard();
        cardGermany.setCountry("Germany");
        cardGermany.setYear(2001);
        
        cards.add(cardPoland);
        cards.add(cardGermany);
        cards.add(cardUkraine);
    }
    
    @Test
    void testSortByYear() {
        cards.sort(CardComparators.byYear());

        assertEquals(1985, cards.get(0).getYear(), "Першою має бути листівка за 1985 рік");
        assertEquals(1992, cards.get(1).getYear());
        assertEquals(2001, cards.get(2).getYear(), "Останньою має бути листівка за 2001 рік");
    }
    
    @Test
    void testSortByCountry() {
        cards.sort(CardComparators.byCountry());

        assertEquals("Germany", cards.get(0).getCountry(), "Першою має бути листівка з Німеччини");
        assertEquals("Poland", cards.get(1).getCountry());
        assertEquals("Ukraine", cards.get(2).getCountry(), "Останньою має бути листівка з України");
    }
}