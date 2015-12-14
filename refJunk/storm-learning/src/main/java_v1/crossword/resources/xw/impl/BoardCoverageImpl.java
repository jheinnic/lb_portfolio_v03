/**
 */
package info.jchein.sandbox.app.crossword.resources.xw.impl;

import info.jchein.sandbox.app.crossword.resources.xw.BoardCoverage;
import info.jchein.sandbox.app.crossword.resources.xw.BonusWordCoverage;
import info.jchein.sandbox.app.crossword.resources.xw.GridWordCoverage;
import info.jchein.sandbox.app.crossword.resources.xw.XwPackage;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Board Coverage</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link info.jchein.sandbox.app.crossword.resources.xw.impl.BoardCoverageImpl#getBonusWord <em>Bonus Word</em>}</li>
 *   <li>{@link info.jchein.sandbox.app.crossword.resources.xw.impl.BoardCoverageImpl#getAllWords <em>All Words</em>}</li>
 *   <li>{@link info.jchein.sandbox.app.crossword.resources.xw.impl.BoardCoverageImpl#getBasicWords <em>Basic Words</em>}</li>
 *   <li>{@link info.jchein.sandbox.app.crossword.resources.xw.impl.BoardCoverageImpl#getTripleWords <em>Triple Words</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class BoardCoverageImpl extends MinimalEObjectImpl.Container implements BoardCoverage {
    /**
     * The cached value of the '{@link #getBonusWord() <em>Bonus Word</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getBonusWord()
     * @generated
     * @ordered
     */
    protected BonusWordCoverage bonusWord;

    /**
     * The cached value of the '{@link #getAllWords() <em>All Words</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getAllWords()
     * @generated
     * @ordered
     */
    protected EList<GridWordCoverage> allWords;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected BoardCoverageImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return XwPackage.Literals.BOARD_COVERAGE;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public BonusWordCoverage getBonusWord() {
        return bonusWord;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetBonusWord(BonusWordCoverage newBonusWord, NotificationChain msgs) {
        BonusWordCoverage oldBonusWord = bonusWord;
        bonusWord = newBonusWord;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, XwPackage.BOARD_COVERAGE__BONUS_WORD, oldBonusWord, newBonusWord);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<GridWordCoverage> getAllWords() {
        if (allWords == null) {
            allWords = new EObjectContainmentEList<GridWordCoverage>(GridWordCoverage.class, this, XwPackage.BOARD_COVERAGE__ALL_WORDS);
        }
        return allWords;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<GridWordCoverage> getBasicWords() {
        // TODO: implement this method to return the 'Basic Words' containment reference list
        // Ensure that you remove @generated or mark it @generated NOT
        // The list is expected to implement org.eclipse.emf.ecore.util.InternalEList and org.eclipse.emf.ecore.EStructuralFeature.Setting
        // so it's likely that an appropriate subclass of org.eclipse.emf.ecore.util.EcoreEList should be used.
        throw new UnsupportedOperationException();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<GridWordCoverage> getTripleWords() {
        // TODO: implement this method to return the 'Triple Words' containment reference list
        // Ensure that you remove @generated or mark it @generated NOT
        // The list is expected to implement org.eclipse.emf.ecore.util.InternalEList and org.eclipse.emf.ecore.EStructuralFeature.Setting
        // so it's likely that an appropriate subclass of org.eclipse.emf.ecore.util.EcoreEList should be used.
        throw new UnsupportedOperationException();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case XwPackage.BOARD_COVERAGE__BONUS_WORD:
                return basicSetBonusWord(null, msgs);
            case XwPackage.BOARD_COVERAGE__ALL_WORDS:
                return ((InternalEList<?>)getAllWords()).basicRemove(otherEnd, msgs);
            case XwPackage.BOARD_COVERAGE__BASIC_WORDS:
                return ((InternalEList<?>)getBasicWords()).basicRemove(otherEnd, msgs);
            case XwPackage.BOARD_COVERAGE__TRIPLE_WORDS:
                return ((InternalEList<?>)getTripleWords()).basicRemove(otherEnd, msgs);
        }
        return super.eInverseRemove(otherEnd, featureID, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case XwPackage.BOARD_COVERAGE__BONUS_WORD:
                return getBonusWord();
            case XwPackage.BOARD_COVERAGE__ALL_WORDS:
                return getAllWords();
            case XwPackage.BOARD_COVERAGE__BASIC_WORDS:
                return getBasicWords();
            case XwPackage.BOARD_COVERAGE__TRIPLE_WORDS:
                return getTripleWords();
        }
        return super.eGet(featureID, resolve, coreType);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public boolean eIsSet(int featureID) {
        switch (featureID) {
            case XwPackage.BOARD_COVERAGE__BONUS_WORD:
                return bonusWord != null;
            case XwPackage.BOARD_COVERAGE__ALL_WORDS:
                return allWords != null && !allWords.isEmpty();
            case XwPackage.BOARD_COVERAGE__BASIC_WORDS:
                return !getBasicWords().isEmpty();
            case XwPackage.BOARD_COVERAGE__TRIPLE_WORDS:
                return !getTripleWords().isEmpty();
        }
        return super.eIsSet(featureID);
    }

} //BoardCoverageImpl
