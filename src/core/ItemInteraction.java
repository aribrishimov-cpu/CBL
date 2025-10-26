package src.core;

import src.cbl.items.Ingredient;

/**
 * Interaction with items such as picking up, dropping off etc.
 */
public class ItemInteraction {
    private Ingredient heldIngredient;

    public ItemInteraction() {
        this.heldIngredient = null;
    }

    /**
     * Cheking if the player holds anything and if no pick up an item.
     */
    public void pickUp(Ingredient ingredient) {
        if (this.heldIngredient == null) {
            this.heldIngredient = ingredient;
            System.out.println("Picked up" + ingredient.getName());
        } else {
            System.out.println("You are already holding " + this.heldIngredient.getName());
        } 
    }

    /**
     * Seeing if the player hold anything and if so drop it.
     */
    public void drop() {
        if (this.heldIngredient != null) {
            this.heldIngredient = null;
            System.out.println("You dropped " + this.heldIngredient.getName());
        } else {
            System.out.println("You are not holding anything");
        }
    }

    public Ingredient getHeldIngredint() {
        return heldIngredient;
    }
}