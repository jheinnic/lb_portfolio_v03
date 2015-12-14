package info.jchein.sandbox.app.crossword.prizecalc.prizecalc.util.builder;

/**
 * <!-- begin-user-doc --> 
 *   A builder for the model object '<em><b>info.jchein.sandbox.app.crossword.prizecalc.prizecalc.TicketState</b></em>'.
 * <!-- end-user-doc -->
 * 
 * @generated
 */
public class TicketStateBuilder implements info.jchein.sandbox.app.crossword.prizecalc.prizecalc.util.builder.IPrizecalcBuilder<info.jchein.sandbox.app.crossword.prizecalc.prizecalc.TicketState> {
  // features and builders
  private Integer m_bonusWordIndex;
  private Boolean m_tripled;
  private Integer m_wordsCompleted;
  private java.util.Collection<info.jchein.sandbox.app.crossword.prizecalc.prizecalc.AvailableLetter> m_availableLetters = new java.util.LinkedList<info.jchein.sandbox.app.crossword.prizecalc.prizecalc.AvailableLetter>();
  private java.util.Collection<info.jchein.sandbox.app.crossword.prizecalc.prizecalc.util.builder.IPrizecalcBuilder<? extends info.jchein.sandbox.app.crossword.prizecalc.prizecalc.AvailableLetter>> m_featureAvailableLettersBuilder = new java.util.LinkedList<info.jchein.sandbox.app.crossword.prizecalc.prizecalc.util.builder.IPrizecalcBuilder<? extends info.jchein.sandbox.app.crossword.prizecalc.prizecalc.AvailableLetter>>();
  private java.util.Collection<Integer> m_incompleteWords = new java.util.LinkedList<Integer>();
  // helper attributes
  private boolean m_featureAvailableLettersSet = false;
  private boolean m_featureBonusWordIndexSet = false;
  private boolean m_featureIncompleteWordsSet = false;
  private boolean m_featureTripledSet = false;
  private boolean m_featureWordsCompletedSet = false;

  /**
   * Builder is not instantiated with a constructor.
   * @see #newTicketStateBuilder()
   */
  private TicketStateBuilder() {
  }

  /**
   * This method creates a new instance of the TicketStateBuilder.
   * @return new instance of the TicketStateBuilder
   */
  public static TicketStateBuilder newTicketStateBuilder() {
    return new TicketStateBuilder();
  }

  /**
   * This method creates a new instance of the TicketStateBuilder. 
   * The builder is initialized using an existing '<em><b>info.jchein.sandbox.app.crossword.prizecalc.prizecalc.TicketState</b></em>' model object.
   * In order to avoid changes to the provided '<em><b>info.jchein.sandbox.app.crossword.prizecalc.prizecalc.TicketState</b></em>' model object, a copy is created using <em><b>org.eclipse.emf.ecore.util.EcoreUtil.Copier</b></em>.
   * @param ticketState The existing '<em><b>info.jchein.sandbox.app.crossword.prizecalc.prizecalc.TicketState</b></em>' model object to be used for the initialization of the builder  
   * @return new initialized instance of the TicketStateBuilder
   */
  public static TicketStateBuilder newTicketStateBuilder(info.jchein.sandbox.app.crossword.prizecalc.prizecalc.TicketState p_ticketState) {
    org.eclipse.emf.ecore.util.EcoreUtil.Copier c = new org.eclipse.emf.ecore.util.EcoreUtil.Copier();
    info.jchein.sandbox.app.crossword.prizecalc.prizecalc.TicketState _ticketState = (info.jchein.sandbox.app.crossword.prizecalc.prizecalc.TicketState) c
        .copy(((info.jchein.sandbox.app.crossword.prizecalc.prizecalc.TicketState) p_ticketState));
    c.copyReferences();
    TicketStateBuilder _builder = newTicketStateBuilder();
    _builder.bonusWordIndex(_ticketState.getBonusWordIndex());
    _builder.tripled(_ticketState.isTripled());
    _builder.wordsCompleted(_ticketState.getWordsCompleted());
    if (_ticketState.getAvailableLetters() != null) {
      _builder.availableLetters(_ticketState.getAvailableLetters());
    }
    if (_ticketState.getIncompleteWords() != null) {
      _builder.incompleteWords(_ticketState.getIncompleteWords());
    }
    return _builder;
  }

  /**
   * This method can be used to override attributes of the builder. It constructs a new builder and copies the current values to it.
   */
  public TicketStateBuilder but() {
    TicketStateBuilder _builder = newTicketStateBuilder();
    _builder.m_featureAvailableLettersSet = m_featureAvailableLettersSet;
    _builder.m_availableLetters = m_availableLetters;
    _builder.m_featureAvailableLettersBuilder = m_featureAvailableLettersBuilder;
    _builder.m_featureBonusWordIndexSet = m_featureBonusWordIndexSet;
    _builder.m_bonusWordIndex = m_bonusWordIndex;
    _builder.m_featureIncompleteWordsSet = m_featureIncompleteWordsSet;
    _builder.m_incompleteWords = m_incompleteWords;
    _builder.m_featureTripledSet = m_featureTripledSet;
    _builder.m_tripled = m_tripled;
    _builder.m_featureWordsCompletedSet = m_featureWordsCompletedSet;
    _builder.m_wordsCompleted = m_wordsCompleted;
    return _builder;
  }

  /**
   * This method constructs the final info.jchein.sandbox.app.crossword.prizecalc.prizecalc.TicketState type.
   * @return new instance of the info.jchein.sandbox.app.crossword.prizecalc.prizecalc.TicketState type
   */
  public info.jchein.sandbox.app.crossword.prizecalc.prizecalc.TicketState build() {
    final info.jchein.sandbox.app.crossword.prizecalc.prizecalc.TicketState _newInstance = (info.jchein.sandbox.app.crossword.prizecalc.prizecalc.TicketState) info.jchein.sandbox.app.crossword.prizecalc.prizecalc.PrizecalcFactory.eINSTANCE
        .create(info.jchein.sandbox.app.crossword.prizecalc.prizecalc.PrizecalcPackage.eINSTANCE.getTicketState());
    if (m_featureBonusWordIndexSet) {
      _newInstance.setBonusWordIndex(m_bonusWordIndex);
    }
    if (m_featureTripledSet) {
      _newInstance.setTripled(m_tripled);
    }
    if (m_featureWordsCompletedSet) {
      _newInstance.setWordsCompleted(m_wordsCompleted);
    }
    if (m_featureAvailableLettersSet) {
      _newInstance.getAvailableLetters().addAll(m_availableLetters);
    } else {
      if (!m_featureAvailableLettersBuilder.isEmpty()) {
        for (info.jchein.sandbox.app.crossword.prizecalc.prizecalc.util.builder.IPrizecalcBuilder<? extends info.jchein.sandbox.app.crossword.prizecalc.prizecalc.AvailableLetter> builder : m_featureAvailableLettersBuilder) {
          _newInstance.getAvailableLetters().add(builder.build());
        }
      }
    }
    if (m_featureIncompleteWordsSet) {
      _newInstance.getIncompleteWords().addAll(m_incompleteWords);
    }
    return _newInstance;
  }

  public TicketStateBuilder bonusWordIndex(Integer p_bonusWordIndex) {
    m_bonusWordIndex = p_bonusWordIndex;
    m_featureBonusWordIndexSet = true;
    return this;
  }

  public TicketStateBuilder tripled(Boolean p_tripled) {
    m_tripled = p_tripled;
    m_featureTripledSet = true;
    return this;
  }

  public TicketStateBuilder wordsCompleted(Integer p_wordsCompleted) {
    m_wordsCompleted = p_wordsCompleted;
    m_featureWordsCompletedSet = true;
    return this;
  }

  public TicketStateBuilder availableLetters(info.jchein.sandbox.app.crossword.prizecalc.prizecalc.AvailableLetter p_availableLetters) {
    m_availableLetters.add(p_availableLetters);
    m_featureAvailableLettersSet = true;
    return this;
  }

  public TicketStateBuilder availableLetters(java.util.Collection<? extends info.jchein.sandbox.app.crossword.prizecalc.prizecalc.AvailableLetter> p_availableLetters) {
    m_availableLetters.addAll(p_availableLetters);
    m_featureAvailableLettersSet = true;
    return this;
  }

  public TicketStateBuilder availableLetters(
      info.jchein.sandbox.app.crossword.prizecalc.prizecalc.util.builder.IPrizecalcBuilder<? extends info.jchein.sandbox.app.crossword.prizecalc.prizecalc.AvailableLetter> p_availableLetterBuilder) {
    m_featureAvailableLettersBuilder.add(p_availableLetterBuilder);
    return this;
  }

  public TicketStateBuilder incompleteWords(Integer p_incompleteWords) {
    m_incompleteWords.add(p_incompleteWords);
    m_featureIncompleteWordsSet = true;
    return this;
  }

  public TicketStateBuilder incompleteWords(java.util.Collection<? extends Integer> p_incompleteWords) {
    m_incompleteWords.addAll(p_incompleteWords);
    m_featureIncompleteWordsSet = true;
    return this;
  }
}
