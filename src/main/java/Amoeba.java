import java.util.Objects;

/**
 * This class represents an Amoeba that starts with a gender of either male or female and a start size of 1.
 */
public class Amoeba {

    /**
     * True for female, false for male.
     */
    private final boolean gender;
    private int size = 1;

    /**
     * Constructor to create a new amoeba with a size of one and a gender.
     *
     * @param gender - Either true for female or false for male.
     */
    public Amoeba(boolean gender) {
        this.gender = gender;
    }

    /**
     * Getter that returns if the amoeba is male or female.
     *
     * @return - Returns either true for female or false for male.
     */
    public boolean isFemale() {
        return gender;
    }

    /**
     * Getter that returns the size of the amoeba.
     *
     * @return - Returns true if female or false if male.
     */
    public int getSize() {
        return size;
    }

    /**
     * Method that feeds the amoeba and increments the size plus 1.
     *
     * @return - Returns this amoeba.
     */
    public Amoeba feed() {
        size++;
        return this;
    }

    /**
     * Method that lets the amoeba hunger and decreases the size of 1.
     *
     * @return - Returns this amoeba.
     */
    public Amoeba hunger() {
        if (getSize() > 1) {
            size--;
        }
        return this;
    }

    /**
     * Method that splits this amoeba and creates a new one. In the end the new amoeba has the same gender
     * and both have now the same size added together like this amoeba hat before splitting.
     * If the size of this amoeba is lower than 5 this method returns null.
     * Example: This amoeba has a size of 11 before splitting. After it, this amoeba has a size of 5 and new one of 6.
     *
     * @return - Returns the new amoeba.
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
     * Method that joins with another amoeba to create a new one.
     * If both amoeba have the same size or the same gender, null gets returned.
     * The new gender will be the gender of the bigger amoeba.
     *
     * @param other - Other amoeba to join.
     * @return - Returns a new amoeba with the gender of its bigger "parent".
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
     * Method that lets fight two amoebas. Only if both are male and one is bigger than the other the fight will begin.
     * The amoeba with smaller size gets graded down to 1 and bigger amoeba gets loser's size added.
     *
     * @param enemy - The enemy amoeba to fight with.
     * @return - Returns this amoeba.
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
     * Method that returns all information of the amoeba (Gender and size).
     *
     * @return - Returns a String with the information.
     */
    @Override
    public String toString() {
        return String.format("Amoeba(%c,%d)", isFemale() ? 'f' : 'm', getSize());
    }

}
