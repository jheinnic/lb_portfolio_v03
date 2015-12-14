/**
 */
package info.jchein.sandbox.app.crossword.resources.xw;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Bonus Value</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see info.jchein.sandbox.app.crossword.resources.xw.XwPackage#getBonusValue()
 * @model
 * @generated
 */
public enum BonusValue implements Enumerator {
    /**
     * The '<em><b>FOUR</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #FOUR_VALUE
     * @generated
     * @ordered
     */
    FOUR(0, "FOUR", "FOUR"),

    /**
     * The '<em><b>FIVE</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #FIVE_VALUE
     * @generated
     * @ordered
     */
    FIVE(1, "FIVE", "FIVE"),

    /**
     * The '<em><b>TEN</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #TEN_VALUE
     * @generated
     * @ordered
     */
    TEN(2, "TEN", "TEN"),

    /**
     * The '<em><b>THIRTY</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #THIRTY_VALUE
     * @generated
     * @ordered
     */
    THIRTY(3, "THIRTY", "THIRTY");

    /**
     * The '<em><b>FOUR</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>FOUR</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #FOUR
     * @model
     * @generated
     * @ordered
     */
    public static final int FOUR_VALUE = 0;

    /**
     * The '<em><b>FIVE</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>FIVE</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #FIVE
     * @model
     * @generated
     * @ordered
     */
    public static final int FIVE_VALUE = 1;

    /**
     * The '<em><b>TEN</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>TEN</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #TEN
     * @model
     * @generated
     * @ordered
     */
    public static final int TEN_VALUE = 2;

    /**
     * The '<em><b>THIRTY</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>THIRTY</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #THIRTY
     * @model
     * @generated
     * @ordered
     */
    public static final int THIRTY_VALUE = 3;

    /**
     * An array of all the '<em><b>Bonus Value</b></em>' enumerators.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private static final BonusValue[] VALUES_ARRAY =
        new BonusValue[] {
            FOUR,
            FIVE,
            TEN,
            THIRTY,
        };

    /**
     * A public read-only list of all the '<em><b>Bonus Value</b></em>' enumerators.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final List<BonusValue> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

    /**
     * Returns the '<em><b>Bonus Value</b></em>' literal with the specified literal value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static BonusValue get(String literal) {
        for (int i = 0; i < VALUES_ARRAY.length; ++i) {
            BonusValue result = VALUES_ARRAY[i];
            if (result.toString().equals(literal)) {
                return result;
            }
        }
        return null;
    }

    /**
     * Returns the '<em><b>Bonus Value</b></em>' literal with the specified name.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static BonusValue getByName(String name) {
        for (int i = 0; i < VALUES_ARRAY.length; ++i) {
            BonusValue result = VALUES_ARRAY[i];
            if (result.getName().equals(name)) {
                return result;
            }
        }
        return null;
    }

    /**
     * Returns the '<em><b>Bonus Value</b></em>' literal with the specified integer value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static BonusValue get(int value) {
        switch (value) {
            case FOUR_VALUE: return FOUR;
            case FIVE_VALUE: return FIVE;
            case TEN_VALUE: return TEN;
            case THIRTY_VALUE: return THIRTY;
        }
        return null;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private final int value;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private final String name;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private final String literal;

    /**
     * Only this class can construct instances.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private BonusValue(int value, String name, String literal) {
        this.value = value;
        this.name = name;
        this.literal = literal;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public int getValue() {
      return value;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getName() {
      return name;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getLiteral() {
      return literal;
    }

    /**
     * Returns the literal value of the enumerator, which is its string representation.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String toString() {
        return literal;
    }
    
} //BonusValue
