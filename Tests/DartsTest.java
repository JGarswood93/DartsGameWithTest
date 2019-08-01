import com.barton.Darts;
import org.junit.Before;
import org.junit.Test;
/**
 * Scoring System in darts
 * Three darts thrown at the dart board
 * You can hit a single number 20, 10, 16,14
 * You can have double of these numbers or triples also
 * You can also hist a bulls-eye.
 */
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class DartsTest {
    /**
     * Before anything is done it must be certain that there is a dart
     * called d, and that the game set up begins with a new dart.
     */
    private Darts d;

    @Before
    public void setUpGame() {
        d = new Darts();
    }

    /**
     * Set the score given to the players
     *
     * This corresponds with the data being called at the start
     * of the script
     */
    @Test
    public void aGameShouldStartAt301() {
        assertThat(d.score(), equalTo(301));
        assertThat(d.isFinished(), is(false));
    }

    /**
     * Test to see that the score will take the score system
     * correctly
     */
    @Test
    public void shouldCorrectlyScoreANormalThrow() {
        d.dart(20);
        assertThat(d.score(), is(281));
    }

    /**
     * Establishing a double point
     *
     * When a double occurs similair logic to the above test method
     * but here it double the point.
     */
    @Test
    public void shouldCountADoubleThrow() {
        d.dart(20, Darts.Multipliers.DOUBLE);
        assertThat(d.score(), is(301 - 20 * 2));
    }

    /**
     * Triple point same logic as above but with an
     * extra power
     */

    @Test
    public void shouldCountATripleThrow() {
        d.dart(20, Darts.Multipliers.TRIPLE);
        assertThat(d.score(), is(301 - 20 * 3));
    }

    /**
     * initial turn
     */
    @Test
    public void shouldCountTheTurnInitially() {
        assertThat(d.getTurn(), is(1));
        assertThat(d.dartsLeft(), is(3));
    }

    /**
     * Here we have the system of turns
     * 1 mean assigned means you have 2 left
     * You can have a turn then there is just
     * one dart left
     * be on your third go and then darts reset
     * ie additional 3 is given
     */
    @Test
    public void shouldCountTheTurn() {

        d.dart(1);
        assertThat(d.getTurn(), is(1));
        assertThat(d.dartsLeft(), is(2));

        d.dart(1);
        assertThat(d.getTurn(), is(1));
        assertThat(d.dartsLeft(), is(1));

        d.dart(1);
        assertThat(d.getTurn(), is(2));
        assertThat(d.dartsLeft(), is(3));

    }

    /**
     * This is what happens if you get triple twenty
     */
    @Test
    public void shouldGoBustReaching1() {
        for (int i = 0; i < 3; i++) {
            d.dart(20, Darts.Multipliers.TRIPLE);
        }
        d.dart(20, Darts.Multipliers.TRIPLE);
        d.dart(20, Darts.Multipliers.TRIPLE);

        assertEquals(121, d.score());
        assertEquals(3, d.getTurn());
        assertEquals(3, d.dartsLeft());
    }

    @Test
    public void shouldGoBustAboveZero() {
        for (int i = 0; i < 3; i++) {
            d.dart(20, Darts.Multipliers.TRIPLE);
        }
        d.dart(15, Darts.Multipliers.TRIPLE);
        d.dart(15, Darts.Multipliers.TRIPLE);
        d.dart(20, Darts.Multipliers.TRIPLE);

        assertEquals(121, d.score());
        assertEquals(3, d.getTurn());
        assertEquals(3, d.dartsLeft());
    }

    @Test
    public void shouldCompleteAGameWithADouble() {
        for (int i = 0; i < 3; i++) {
            d.dart(20, Darts.Multipliers.TRIPLE);
        }

        d.dart(17, Darts.Multipliers.TRIPLE);
        d.dart(20, Darts.Multipliers.TRIPLE);
        d.dart(5, Darts.Multipliers.TRIPLE);
    }
}
