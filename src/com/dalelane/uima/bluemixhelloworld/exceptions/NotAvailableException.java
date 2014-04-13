package com.dalelane.uima.bluemixhelloworld.exceptions;

/**
 * An exception used to indicate that the pipeline is currently busy processing
 *  multiple other concurrent requests and was unable to process the request
 *  before timeout.
 * 
 * @author Dale Lane (email@dalelane.co.uk)
 */
public class NotAvailableException extends Exception { }
