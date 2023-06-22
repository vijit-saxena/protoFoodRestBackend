package com.example.protoFoodV2.exceptions;

public class EntityNotFoundException extends DalException {
    public EntityNotFoundException(EntityType entityType, Object key) {
        super(String.format("%s entity with key %s not found", entityType, key == null ? "" : key.toString()));
    }

    public EntityNotFoundException(Object key) {
        super(String.format("Entity with key %s not found", key == null? "" : key.toString()));
    }

    public enum EntityType {
        Extra, Skip, Tiffin, Taste, Order, Payment, Subscription, Location, User
    }
}
