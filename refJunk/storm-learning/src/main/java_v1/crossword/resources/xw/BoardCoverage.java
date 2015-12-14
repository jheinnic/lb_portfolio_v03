/**
 */
package info.jchein.sandbox.app.crossword.resources.xw;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Board Coverage</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link info.jchein.sandbox.app.crossword.resources.xw.BoardCoverage#getBonusWord <em>Bonus Word</em>}</li>
 *   <li>{@link info.jchein.sandbox.app.crossword.resources.xw.BoardCoverage#getAllWords <em>All Words</em>}</li>
 *   <li>{@link info.jchein.sandbox.app.crossword.resources.xw.BoardCoverage#getBasicWords <em>Basic Words</em>}</li>
 *   <li>{@link info.jchein.sandbox.app.crossword.resources.xw.BoardCoverage#getTripleWords <em>Triple Words</em>}</li>
 * </ul>
 * </p>
 *
 * @see info.jchein.sandbox.app.crossword.resources.xw.XwPackage#getBoardCoverage()
 * @model
 * @generated
 */
public interface BoardCoverage extends EObject {
    /**
     * Returns the value of the '<em><b>Bonus Word</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Bonus Word</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Bonus Word</em>' containment reference.
     * @see info.jchein.sandbox.app.crossword.resources.xw.XwPackage#getBoardCoverage_BonusWord()
     * @model containment="true" required="true" changeable="false"
     * @generated
     */
    BonusWordCoverage getBonusWord();

    /**
     * Returns the value of the '<em><b>All Words</b></em>' containment reference list.
     * The list contents are of type {@link info.jchein.sandbox.app.crossword.resources.xw.GridWordCoverage}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>All Words</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>All Words</em>' containment reference list.
     * @see info.jchein.sandbox.app.crossword.resources.xw.XwPackage#getBoardCoverage_AllWords()
     * @model containment="true" lower="22" upper="22" changeable="false"
     * @generated
     */
    EList<GridWordCoverage> getAllWords();

    /**
     * Returns the value of the '<em><b>Basic Words</b></em>' containment reference list.
     * The list contents are of type {@link info.jchein.sandbox.app.crossword.resources.xw.GridWordCoverage}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Basic Words</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Basic Words</em>' containment reference list.
     * @see info.jchein.sandbox.app.crossword.resources.xw.XwPackage#getBoardCoverage_BasicWords()
     * @model containment="true" lower="18" upper="18" transient="true" changeable="false" volatile="true" derived="true"
     * @generated
     */
    EList<GridWordCoverage> getBasicWords();

    /**
     * Returns the value of the '<em><b>Triple Words</b></em>' containment reference list.
     * The list contents are of type {@link info.jchein.sandbox.app.crossword.resources.xw.GridWordCoverage}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Triple Words</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Triple Words</em>' containment reference list.
     * @see info.jchein.sandbox.app.crossword.resources.xw.XwPackage#getBoardCoverage_TripleWords()
     * @model containment="true" lower="4" upper="4" transient="true" changeable="false" volatile="true" derived="true"
     * @generated
     */
    EList<GridWordCoverage> getTripleWords();

} // BoardCoverage
