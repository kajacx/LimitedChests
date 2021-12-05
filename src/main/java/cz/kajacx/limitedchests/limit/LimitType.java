package cz.kajacx.limitedchests.limit;

public enum LimitType {

    /** Allow any item in this slot (default) */
    ANY,

    /** Limit only count of items, and not item type */
    COUNT,

    /** Filter item type in this slot, but do not restrict other slots */
    FILTER,

    /** Restrict item only to this slot (or other slots with this item explicitly) */
    RESTRICT,

    /** Completely disable slot */
    DISABLE;

    
}
