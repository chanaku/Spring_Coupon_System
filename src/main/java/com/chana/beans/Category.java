package com.chana.beans;

/**
 * 
 * Enum class of category. this class is no entity, but only enum. for using to
 * others entity's classes.
 * 
 * @see 'null' is because the enum is like an array, the first one is number 0. so
 *      to start from 1, (because the DB) we put somthing like null in the first
 *      place.
 */

public enum Category {
	Null, Food, Electricity, Restaurant, Vacation;

}
