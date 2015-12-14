/**
 */
package info.jchein.sandbox.app.crossword.prizecalc.prizecalc;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Begin Calculation</b></em>'. <!--
 * end-user-doc -->
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link info.jchein.sandbox.app.crossword.prizecalc.prizecalc.BeginCalculation#getTicketHref <em>
 * Ticket Href</em>}</li>
 * <li>{@link info.jchein.sandbox.app.crossword.prizecalc.prizecalc.BeginCalculation#getTicketState <em>
 * Ticket State</em>}</li>
 * </ul>
 * </p>
 * 
 * @see info.jchein.sandbox.app.crossword.prizecalc.prizecalc.PrizecalcPackage#getBeginCalculation()
 * @model
 * @generated
 */
public interface BeginCalculation extends EObject {
    /**
     * Returns the value of the '<em><b>Ticket Href</b></em>' attribute. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Ticket Href</em>' attribute isn't clear, there really should be more of a
     * description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Ticket Href</em>' attribute.
     * @see #setTicketHref(String)
     * @see info.jchein.sandbox.app.crossword.prizecalc.prizecalc.PrizecalcPackage#getBeginCalculation_TicketHref()
     * @model
     * @generated
     */
    String getTicketHref( );

    /**
     * Sets the value of the '
     * {@link info.jchein.sandbox.app.crossword.prizecalc.prizecalc.BeginCalculation#getTicketHref
     * <em>Ticket Href</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Ticket Href</em>' attribute.
     * @see #getTicketHref()
     * @generated
     */
    void setTicketHref( String value );

    /**
     * Returns the value of the '<em><b>Ticket State</b></em>' reference. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Ticket State</em>' reference isn't clear, there really should be more of a
     * description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Ticket State</em>' reference.
     * @see #setTicketState(TicketState)
     * @see info.jchein.sandbox.app.crossword.prizecalc.prizecalc.PrizecalcPackage#getBeginCalculation_TicketState()
     * @model
     * @generated
     */
    TicketState getTicketState( );

    /**
     * Sets the value of the '
     * {@link info.jchein.sandbox.app.crossword.prizecalc.prizecalc.BeginCalculation#getTicketState
     * <em>Ticket State</em>}' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Ticket State</em>' reference.
     * @see #getTicketState()
     * @generated
     */
    void setTicketState( TicketState value );

} // BeginCalculation
