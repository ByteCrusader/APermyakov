package ru.apermyakov.testtask.requests;

/**
 * Main interface for requests.
 *
 * @author apermyakov.
 * @version 1.0.
 * @since 12.01.2018.
 */
public interface Request extends PriorityRequest, SizeRequest, ComplexityRequest, CoordRequest, ValueRequest, ExitRequest {
}
