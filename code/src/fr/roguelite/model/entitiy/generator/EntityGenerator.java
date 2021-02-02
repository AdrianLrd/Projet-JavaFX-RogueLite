package fr.roguelite.model.entitiy.generator;

import fr.roguelite.model.entitiy.Entity;
import fr.roguelite.model.entitiy.EntityType;

public abstract class EntityGenerator {
   /**
    * Create a new EntityGenerator, you need to override methods because EntityGenerator is abstract
    */
   public EntityGenerator() {

   }

   /**
    * Generate a new Entity with the given type
    * @param type The type of the Entity to be created
    * @return The newly Entity created
    */
   public abstract Entity generate(EntityType type);
}
