package pl.mylittleworld.database.tables;

class IllegalUsageException extends RuntimeException {

    @Override
    public String toString() {
        return "Not instance of specified type of Condition";
    }
}
