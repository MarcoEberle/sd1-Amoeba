import static org.junit.Assert.*;

import org.junit.Test;

public class AmoebaTest {


    @org.junit.Test
    public void isFemale() {
        //Arrange
        Amoeba sut = new Amoeba(true);
        final boolean want = true;
        //Act
        final boolean have = sut.isFemale();
        //Assert
        assertEquals(want, have);
    }

    @org.junit.Test
    public void getSize() {
        //Arrange
        Amoeba sut = new Amoeba(true);
        final int want = 1;
        //Act
        final int have = sut.getSize();
        //Assert
        assertEquals(want, have);
    }

    @org.junit.Test
    public void feed() {
        //Arrange
        Amoeba sut = new Amoeba(true);
        sut.feed();
        sut.feed();
        final int want = 3;
        //Act
        final int have = sut.getSize();
        //Assert
        assertEquals(want, have);
    }

    @org.junit.Test
    public void hunger() {
        //Arrange
        Amoeba sut = new Amoeba(false);
        sut.hunger();
        final int want = 1;
        //Act
        final int have = sut.getSize();
        //Assert
        assertEquals(want, have);
    }


    @org.junit.Test
    public void split() {
        //Arrange
        Amoeba sut = new Amoeba(true);
        final int wantSut = 5;
        final int wantSplit = 6;
        //Act
        for (int testIndex = 0; testIndex < 10; testIndex++) {
            sut.feed();
        }
        //Assert
        assertEquals(wantSut, sut.split().getSize());
        assertEquals(wantSplit, sut.getSize());
    }

    @org.junit.Test
    public void join() {
        //Arrange
        Amoeba sut = new Amoeba(true);
        Amoeba other = new Amoeba(false);
        final boolean wantGender = true;
        //Act
        sut.feed();
        //Assert
        assertEquals(wantGender, sut.join(other).isFemale());
    }

    @org.junit.Test
    public void attack() {
        //Arrange
        Amoeba sut = new Amoeba(false);
        Amoeba other = new Amoeba(false);
        final int wantSut = 2;
        final int wantOther = 1;
        //Act
        sut.feed();
        //Assert
        assertEquals(wantSut, sut.attack(other).getSize());
        assertEquals(wantOther, other.getSize());
    }
}