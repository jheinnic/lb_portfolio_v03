/**
 */
package info.jchein.sandbox.app.crossword.prizecalc.prizecalc;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Ticket State</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link info.jchein.sandbox.app.crossword.prizecalc.prizecalc.TicketState#getBonusWordIndex <em>Bonus Word Index</em>}</li>
 *   <li>{@link info.jchein.sandbox.app.crossword.prizecalc.prizecalc.TicketState#isTripled <em>Tripled</em>}</li>
 *   <li>{@link info.jchein.sandbox.app.crossword.prizecalc.prizecalc.TicketState#getWordsCompleted <em>Words Completed</em>}</li>
 *   <li>{@link info.jchein.sandbox.app.crossword.prizecalc.prizecalc.TicketState#getIncompleteWords <em>Incomplete Words</em>}</li>
 *   <li>{@link info.jchein.sandbox.app.crossword.prizecalc.prizecalc.TicketState#getAvailableLetters <em>Available Letters</em>}</li>
 * </ul>
 * </p>
 *
 * @see info.jchein.sandbox.app.crossword.prizecalc.prizecalc.PrizecalcPackage#getTicketState()
 * @model annotation="http://www.eclipse.org/emf/2002/Ecore documentation='A rearrangement of the reusable information from Task input, optimized for the analysis task.'"
 * @generated
 */
public interface TicketState extends EObject {
    /**
     * Returns the value of the '<em><b>Bonus Word Index</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Bonus Word Index</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Bonus Word Index</em>' attribute.
     * @see info.jchein.sandbox.app.crossword.prizecalc.prizecalc.PrizecalcPackage#getTicketState_BonusWordIndex()
     * @model changeable="false"
     *        annotation="http://www.eclipse.org/emf/2002/Ecore documentation='If the bonus word has been covered, this value will be -1.  Otherwise, it will be the index of both incompleteWords and wordHit BitMask that represents the bonus word.'"
     * @generated
     */
    int getBonusWordIndex();

    /**
     * Returns the value of the '<em><b>Tripled</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Tripled</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Tripled</em>' attribute.
     * @see info.jchein.sandbox.app.crossword.prizecalc.prizecalc.PrizecalcPackage#getTicketState_Tripled()
     * @model changeable="false"
     *        annotation="http://www.eclipse.org/emf/2002/Ecore documentation='If true, at least one tripling word has already been covered.  If false, then the incomplete words at indices 0..3 are qualifiers for the triple bonus'"
     * @generated
     */
    boolean isTripled();

    /**
     * Returns the value of the '<em><b>Words Completed</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Words Completed</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Words Completed</em>' attribute.
     * @see info.jchein.sandbox.app.crossword.prizecalc.prizecalc.PrizecalcPackage#getTicketState_WordsCompleted()
     * @model changeable="false"
     *        annotation="http://www.eclipse.org/emf/2002/Ecore documentation='The number of crossword grid words already covered and therefore excluded from incompleteWords and the wordHit BitMask'"
     * @generated
     */
    int getWordsCompleted();

    /**
     * Returns the value of the '<em><b>Incomplete Words</b></em>' attribute list.
     * The list contents are of type {@link java.lang.Integer}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Incomplete Words</em>' attribute list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Incomplete Words</em>' attribute list.
     * @see info.jchein.sandbox.app.crossword.prizecalc.prizecalc.PrizecalcPackage#getTicketState_IncompleteWords()
     * @model lower="13" upper="23" changeable="false"
     *        annotation="http://www.eclipse.org/emf/2002/Ecore documentation='Each array value gives the number of unique letters remaining to cover an incomplete word.  For each AvailableLetter in the availableLetters property that corresponds to one of those missing letters, that object\'s wordHit BitMask has a bit set at the same index.'"
     * @generated
     */
    EList<Integer> getIncompleteWords();

    /**
     * Returns the value of the '<em><b>Available Letters</b></em>' containment reference list.
     * The list contents are of type {@link info.jchein.sandbox.app.crossword.prizecalc.prizecalc.AvailableLetter}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Available Letters</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Available Letters</em>' containment reference list.
     * @see info.jchein.sandbox.app.crossword.prizecalc.prizecalc.PrizecalcPackage#getTicketState_AvailableLetters()
     * @model containment="true" lower="9" upper="26" changeable="false"
     *        annotation="http://www.eclipse.org/emf/2002/Ecore documentation='A collection of objects representing the unique letters not yet uncovered and an associated BitMask selecting the indices of wordsCompleted that represent words that need the associated letter.'"
     * @generated
     */
    EList<AvailableLetter> getAvailableLetters();

} // TicketState
