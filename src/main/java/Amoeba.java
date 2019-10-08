import java.util.Objects;

/**
 * This class represents an Amoeba that starts with a gender of either male or female and a start size of 1.
 */
public class Amoeba {

    /**
     * True for female, false for male.
     */
    private final boolean gender;
    /**
     * When an Amoeba is created, its size is 1.
     */
    private int size = 1;

    /**
     * Constructor to create a new Amoeba with a size of one and a gender.
     *
     * @param gender - Either true for female or false for male.
     */
    public Amoeba(boolean gender) {
        this.gender = gender;
    }

    /**
     * Getter that returns, if this Amoeba is male or female.
     *
     * @return - Returns either true for female or false for male.
     */
    public boolean isFemale() {
        return gender;
    }

    /**
     * Getter that returns the size of this Amoeba.
     *
     * @return - Returns the size of this Amoeba.
     */
    public int getSize() {
        return size;
    }

    /**
     * Method that feeds this Amoeba and increments the size by 1.
     *
     * @return - Returns this Amoeba.
     */
    public Amoeba feed() {
        size++;
        return this;
    }

    /**
     * Method that lets this Amoeba hunger and decreases the size by 1.
     *
     * @return - Returns this Amoeba.
     */
    public Amoeba hunger() {
        if (getSize() > 1) {
            size--;
        }
        return this;
    }

    /**
     * Method that splits this Amoeba and creates a new one. In the end the new Amoeba has the same gender
     * and both have now the same size added together like this amoeba had before splitting.
     * If the size of this Amoeba is lower than 5 this method returns null.
     * Example: This Amoeba has a size of 11 before splitting. After it, this Amoeba has a size of 5 and the new one of 6.
     *
     * @return - Returns the new Amoeba.
     */
    public Amoeba split() {
        final int oldSize = getSize();
        if (getSize() < 5) {
            return null;
        } else {
            Amoeba splitAmoeba = new Amoeba(isFemale());
            for (int indexSize = 0; indexSize < oldSize / 2; indexSize++) {
                hunger();
                splitAmoeba.feed();
            }
            splitAmoeba.hunger();
            return splitAmoeba;
        }
    }

    /**
     * Method that joins with another Amoeba to create a new one.
     * If both Amoeba have the same size or the same gender, null gets returned.
     * The new gender will be the gender of the bigger Amoeba.
     *
     * @param other - Other Amoeba to join.
     * @return - Returns a new Amoeba with the gender of its bigger "parent".
     */
    public Amoeba join(Amoeba other) {

        if (isFemale() == other.isFemale() || getSize() == other.getSize()) {
            return null;
        } else {
            final boolean futureGender = getSize() >= other.getSize() ? isFemale() : other.isFemale();
            return new Amoeba(futureGender);
        }
    }

    /**
     * Method that lets two Amoebas fight. Only if both are male and one is bigger than the other the fight will begin.
     * The Amoeba with smaller size gets graded down to 1 and bigger Amoeba gets loser's size added.
     *
     * @param enemy - The enemy Amoeba to fight with.
     * @return - Returns this Amoeba.
     */
    public Amoeba attack(Amoeba enemy) {

        final boolean isMaleAndDifferent = !isFemale() && !enemy.isFemale() && getSize() != enemy.getSize();
        if (isMaleAndDifferent) {
            final boolean thisBiggerEnemy = enemy.getSize() < getSize();
            if (thisBiggerEnemy) {
                while (enemy.getSize() > 1) {
                    enemy.hunger();
                    feed();
                }
            } else {
                while (this.getSize() > 1) {
                    hunger();
                    enemy.feed();
                }
            }
            return this;
        } else {
            return this;
        }
    }

    /**
     * Method that returns all information of this Amoeba (Gender and size).
     *
     * @return - Returns a String with the information.
     */
    @Override
    public String toString() {
        return String.format("Amoeba(%c,%d)", isFemale() ? 'f' : 'm', getSize());
    }

}
