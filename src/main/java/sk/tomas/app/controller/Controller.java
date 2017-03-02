package sk.tomas.app.controller;

import sk.tomas.app.exception.InputValidationException;
import sk.tomas.app.exception.OutputValidationException;
import sk.tomas.app.iam.model.output.Count;

import java.util.List;
import java.util.UUID;

/**
 * Created by Tomas Pachnik on 01-Mar-17.
 */
public interface Controller<T, N, R> {

    UUID create(T t) throws InputValidationException;

    void delete(UUID uuid) throws InputValidationException;

    N getSingle(UUID uuid) throws InputValidationException, OutputValidationException;

    List<N> list() throws OutputValidationException;

    R listWithParam(int firstResult, int maxResult, String orderBy, boolean desc) throws InputValidationException, OutputValidationException;

    Count getCount() throws OutputValidationException;

    void update(UUID uuid, T t) throws InputValidationException;

    Controller getController();

}
