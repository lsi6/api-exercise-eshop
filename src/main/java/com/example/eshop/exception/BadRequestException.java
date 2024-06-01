package com.example.eshop.exception;

/**
 * Exception to be thrown when a request to the service is found to be invalid.
 */
public class BadRequestException extends Exception
{
    /**
     * The error message explaining the cause of the exception
     */
    private final String errorMessage;

    /**
     * Constructor
     *
     * @param errorMessage - The error message explaining the cause of the exception
     */
    public BadRequestException(final String errorMessage)
    {
        super();
        this.errorMessage = errorMessage;
    }

    /**
     * Method to get the error message.
     *
     * @return - The error message string
     */
    public String getErrorMessage()
    {
        return this.errorMessage;
    }
}
