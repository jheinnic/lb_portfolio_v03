/**
 */
package info.jchein.sandbox.app.crossword.resources.xw;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Abstract Word Coverage</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link info.jchein.sandbox.app.crossword.resources.xw.AbstractWordCoverage#getRawWord <em>Raw Word
 * </em>}</li>
 * <li>{@link info.jchein.sandbox.app.crossword.resources.xw.AbstractWordCoverage#getDisplayWord <em>Display
 * Word</em>}</li>
 * <li>{@link info.jchein.sandbox.app.crossword.resources.xw.AbstractWordCoverage#getUncoveredLetters <em>
 * Uncovered Letters</em>}</li>
 * </ul>
 * </p>
 * 
 * @see info.jchein.sandbox.app.crossword.resources.xw.XwPackage#getAbstractWordCoverage()
 * @model abstract="true"
 * @generated
 */
public interface AbstractWordCoverage extends EObject {
    /**
     * Returns the value of the '<em><b>Raw Word</b></em>' attribute. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Raw Word</em>' attribute isn't clear, there really should be more of a
     * description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Raw Word</em>' attribute.
     * @see #setRawWord(String)
     * @see info.jchein.sandbox.app.crossword.resources.xw.XwPackage#getAbstractWordCoverage_RawWord()
     * @model
     * @generated
     */
    String getRawWord( );

    /**
     * Sets the value of the '
     * {@link info.jchein.sandbox.app.crossword.resources.xw.AbstractWordCoverage#getRawWord
     * <em>Raw Word</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Raw Word</em>' attribute.
     * @see #getRawWord()
     * @generated
     */
    void setRawWord( String value );

    /**
     * Returns the value of the '<em><b>Display Word</b></em>' attribute. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Display Word</em>' attribute isn't clear, there really should be more of a
     * description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Display Word</em>' attribute.
     * @see #setDisplayWord(String)
     * @see info.jchein.sandbox.app.crossword.resources.xw.XwPackage#getAbstractWordCoverage_DisplayWord()
     * @model
     * @generated
     */
    String getDisplayWord( );

    /**
     * Sets the value of the '
     * {@link info.jchein.sandbox.app.crossword.resources.xw.AbstractWordCoverage#getDisplayWord
     * <em>Display Word</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Display Word</em>' attribute.
     * @see #getDisplayWord()
     * @generated
     */
    void setDisplayWord( String value );

    /**
     * Returns the value of the '<em><b>Uncovered Letters</b></em>' attribute. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Uncovered Letters</em>' attribute isn't clear, there really should be more
     * of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Uncovered Letters</em>' attribute.
     * @see info.jchein.sandbox.app.crossword.resources.xw.XwPackage#getAbstractWordCoverage_UncoveredLetters()
     * @model transient="true" changeable="false" volatile="true" derived="true"
     * @generated
     */
    String getUncoveredLetters( );

} // AbstractWordCoverage
