package pl.mylittleworld.database.tables;

/**
 * This is a class of exeption which is thrown when type of Condition doesn't correspond with its object type
 */
class IllegalUsageException extends RuntimeException {

    @Override
    public String toString() {
        return "Not instance of specified type of Condition";
    }
}
