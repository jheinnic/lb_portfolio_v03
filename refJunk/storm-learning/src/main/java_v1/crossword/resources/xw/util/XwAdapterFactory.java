/**
 */
package info.jchein.sandbox.app.crossword.resources.xw.util;

import info.jchein.sandbox.app.crossword.resources.xw.*;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see info.jchein.sandbox.app.crossword.resources.xw.XwPackage
 * @generated
 */
public class XwAdapterFactory extends AdapterFactoryImpl {
    /**
     * The cached model package.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected static XwPackage modelPackage;

    /**
     * Creates an instance of the adapter factory.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public XwAdapterFactory() {
        if (modelPackage == null) {
            modelPackage = XwPackage.eINSTANCE;
        }
    }

    /**
     * Returns whether this factory is applicable for the type of the object.
     * <!-- begin-user-doc -->
     * This implementation returns <code>true</code> if the object is either the model's package or is an instance object of the model.
     * <!-- end-user-doc -->
     * @return whether this factory is applicable for the type of the object.
     * @generated
     */
    @Override
    public boolean isFactoryForType(Object object) {
        if (object == modelPackage) {
            return true;
        }
        if (object instanceof EObject) {
            return ((EObject)object).eClass().getEPackage() == modelPackage;
        }
        return false;
    }

    /**
     * The switch that delegates to the <code>createXXX</code> methods.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected XwSwitch<Adapter> modelSwitch =
        new XwSwitch<Adapter>() {
            @Override
            public Adapter casePendingTicketDetail(PendingTicketDetail object) {
                return createPendingTicketDetailAdapter();
            }
            @Override
            public Adapter casePublishedTicketDetail(PublishedTicketDetail object) {
                return createPublishedTicketDetailAdapter();
            }
            @Override
            public Adapter caseBoardCoverage(BoardCoverage object) {
                return createBoardCoverageAdapter();
            }
            @Override
            public Adapter caseAbstractWordCoverage(AbstractWordCoverage object) {
                return createAbstractWordCoverageAdapter();
            }
            @Override
            public Adapter caseBonusWordCoverage(BonusWordCoverage object) {
                return createBonusWordCoverageAdapter();
            }
            @Override
            public Adapter caseGridWordCoverage(GridWordCoverage object) {
                return createGridWordCoverageAdapter();
            }
            @Override
            public Adapter caseRevealedInformation(RevealedInformation object) {
                return createRevealedInformationAdapter();
            }
            @Override
            public Adapter casePrizeAnalysis(PrizeAnalysis object) {
                return createPrizeAnalysisAdapter();
            }
            @Override
            public Adapter casePlayableTicket(PlayableTicket object) {
                return createPlayableTicketAdapter();
            }
            @Override
            public Adapter caseResolvedTicket(ResolvedTicket object) {
                return createResolvedTicketAdapter();
            }
            @Override
            public Adapter defaultCase(EObject object) {
                return createEObjectAdapter();
            }
        };

    /**
     * Creates an adapter for the <code>target</code>.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param target the object to adapt.
     * @return the adapter for the <code>target</code>.
     * @generated
     */
    @Override
    public Adapter createAdapter(Notifier target) {
        return modelSwitch.doSwitch((EObject)target);
    }


    /**
     * Creates a new adapter for an object of class '{@link info.jchein.sandbox.app.crossword.resources.xw.PendingTicketDetail <em>Pending Ticket Detail</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see info.jchein.sandbox.app.crossword.resources.xw.PendingTicketDetail
     * @generated
     */
    public Adapter createPendingTicketDetailAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link info.jchein.sandbox.app.crossword.resources.xw.PublishedTicketDetail <em>Published Ticket Detail</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see info.jchein.sandbox.app.crossword.resources.xw.PublishedTicketDetail
     * @generated
     */
    public Adapter createPublishedTicketDetailAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link info.jchein.sandbox.app.crossword.resources.xw.BoardCoverage <em>Board Coverage</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see info.jchein.sandbox.app.crossword.resources.xw.BoardCoverage
     * @generated
     */
    public Adapter createBoardCoverageAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link info.jchein.sandbox.app.crossword.resources.xw.AbstractWordCoverage <em>Abstract Word Coverage</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see info.jchein.sandbox.app.crossword.resources.xw.AbstractWordCoverage
     * @generated
     */
    public Adapter createAbstractWordCoverageAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link info.jchein.sandbox.app.crossword.resources.xw.BonusWordCoverage <em>Bonus Word Coverage</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see info.jchein.sandbox.app.crossword.resources.xw.BonusWordCoverage
     * @generated
     */
    public Adapter createBonusWordCoverageAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link info.jchein.sandbox.app.crossword.resources.xw.GridWordCoverage <em>Grid Word Coverage</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see info.jchein.sandbox.app.crossword.resources.xw.GridWordCoverage
     * @generated
     */
    public Adapter createGridWordCoverageAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link info.jchein.sandbox.app.crossword.resources.xw.RevealedInformation <em>Revealed Information</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see info.jchein.sandbox.app.crossword.resources.xw.RevealedInformation
     * @generated
     */
    public Adapter createRevealedInformationAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link info.jchein.sandbox.app.crossword.resources.xw.PrizeAnalysis <em>Prize Analysis</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see info.jchein.sandbox.app.crossword.resources.xw.PrizeAnalysis
     * @generated
     */
    public Adapter createPrizeAnalysisAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link info.jchein.sandbox.app.crossword.resources.xw.PlayableTicket <em>Playable Ticket</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see info.jchein.sandbox.app.crossword.resources.xw.PlayableTicket
     * @generated
     */
    public Adapter createPlayableTicketAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link info.jchein.sandbox.app.crossword.resources.xw.ResolvedTicket <em>Resolved Ticket</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see info.jchein.sandbox.app.crossword.resources.xw.ResolvedTicket
     * @generated
     */
    public Adapter createResolvedTicketAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for the default case.
     * <!-- begin-user-doc -->
     * This default implementation returns null.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @generated
     */
    public Adapter createEObjectAdapter() {
        return null;
    }

} //XwAdapterFactory
