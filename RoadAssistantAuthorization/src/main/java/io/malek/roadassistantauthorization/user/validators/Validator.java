package io.malek.roadassistantauthorization.user.validators;

import java.util.Set;

import static java.util.Objects.isNull;

abstract class Validator<T, R, V> {
    private Validator<T, R, V> next;

    protected static <T, R, V> Validator<T, R, V> link(Validator<T, R, V> first, Validator<T, R, V>... chain) {
        Validator<T, R, V> head = first;
        for (Validator<T, R, V> nextInChain : chain) {
            head.next = nextInChain;
            head = nextInChain;
        }
        return first;
    }

    protected abstract Set<R> validate(T object, Set<R> validatorsInfos);
    protected abstract V getValidatorName();

    protected Set<R> validateNext(T object, Set<R> validatorsInfos) {
        if (isNull(next)) {
            return validatorsInfos;
        }
        return next.validate(object, validatorsInfos);
    }

}
