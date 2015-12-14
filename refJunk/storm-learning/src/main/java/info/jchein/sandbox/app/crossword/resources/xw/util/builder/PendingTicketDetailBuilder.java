package info.jchein.sandbox.app.crossword.resources.xw.util.builder;

/**
 * <!-- begin-user-doc --> 
 *   A builder for the model object '<em><b>info.jchein.sandbox.app.crossword.resources.xw.PendingTicketDetail</b></em>'.
 * <!-- end-user-doc -->
 * 
 * @generated
 */
public class PendingTicketDetailBuilder implements info.jchein.sandbox.app.crossword.resources.xw.util.builder.IXwBuilder<info.jchein.sandbox.app.crossword.resources.xw.PendingTicketDetail> {
  // features and builders
  private java.lang.String m_boardContent;
  private java.lang.String m_bonusWord;
  private Boolean m_twentySpotIncluded;
  // helper attributes
  private boolean m_featureBoardContentSet = false;
  private boolean m_featureBonusWordSet = false;
  private boolean m_featureTwentySpotIncludedSet = false;

  /**
   * Builder is not instantiated with a constructor.
   * @see #newPendingTicketDetailBuilder()
   */
  private PendingTicketDetailBuilder() {
  }

  /**
   * This method creates a new instance of the PendingTicketDetailBuilder.
   * @return new instance of the PendingTicketDetailBuilder
   */
  public static PendingTicketDetailBuilder newPendingTicketDetailBuilder() {
    return new PendingTicketDetailBuilder();
  }

  /**
   * This method creates a new instance of the PendingTicketDetailBuilder. 
   * The builder is initialized using an existing '<em><b>info.jchein.sandbox.app.crossword.resources.xw.PendingTicketDetail</b></em>' model object.
   * In order to avoid changes to the provided '<em><b>info.jchein.sandbox.app.crossword.resources.xw.PendingTicketDetail</b></em>' model object, a copy is created using <em><b>org.eclipse.emf.ecore.util.EcoreUtil.Copier</b></em>.
   * @param pendingTicketDetail The existing '<em><b>info.jchein.sandbox.app.crossword.resources.xw.PendingTicketDetail</b></em>' model object to be used for the initialization of the builder  
   * @return new initialized instance of the PendingTicketDetailBuilder
   */
  public static PendingTicketDetailBuilder newPendingTicketDetailBuilder(info.jchein.sandbox.app.crossword.resources.xw.PendingTicketDetail p_pendingTicketDetail) {
    org.eclipse.emf.ecore.util.EcoreUtil.Copier c = new org.eclipse.emf.ecore.util.EcoreUtil.Copier();
    info.jchein.sandbox.app.crossword.resources.xw.PendingTicketDetail _pendingTicketDetail = (info.jchein.sandbox.app.crossword.resources.xw.PendingTicketDetail) c
        .copy(((info.jchein.sandbox.app.crossword.resources.xw.PendingTicketDetail) p_pendingTicketDetail));
    c.copyReferences();
    PendingTicketDetailBuilder _builder = newPendingTicketDetailBuilder();
    _builder.boardContent(_pendingTicketDetail.getBoardContent());
    _builder.bonusWord(_pendingTicketDetail.getBonusWord());
    _builder.twentySpotIncluded(_pendingTicketDetail.isTwentySpotIncluded());
    return _builder;
  }

  /**
   * This method can be used to override attributes of the builder. It constructs a new builder and copies the current values to it.
   */
  public PendingTicketDetailBuilder but() {
    PendingTicketDetailBuilder _builder = newPendingTicketDetailBuilder();
    _builder.m_featureBoardContentSet = m_featureBoardContentSet;
    _builder.m_boardContent = m_boardContent;
    _builder.m_featureBonusWordSet = m_featureBonusWordSet;
    _builder.m_bonusWord = m_bonusWord;
    _builder.m_featureTwentySpotIncludedSet = m_featureTwentySpotIncludedSet;
    _builder.m_twentySpotIncluded = m_twentySpotIncluded;
    return _builder;
  }

  /**
   * This method constructs the final info.jchein.sandbox.app.crossword.resources.xw.PendingTicketDetail type.
   * @return new instance of the info.jchein.sandbox.app.crossword.resources.xw.PendingTicketDetail type
   */
  public info.jchein.sandbox.app.crossword.resources.xw.PendingTicketDetail build() {
    final info.jchein.sandbox.app.crossword.resources.xw.PendingTicketDetail _newInstance = (info.jchein.sandbox.app.crossword.resources.xw.PendingTicketDetail) info.jchein.sandbox.app.crossword.resources.xw.XwFactory.eINSTANCE
        .create(info.jchein.sandbox.app.crossword.resources.xw.XwPackage.eINSTANCE.getPendingTicketDetail());
    if (m_featureBoardContentSet) {
      _newInstance.setBoardContent(m_boardContent);
    }
    if (m_featureBonusWordSet) {
      _newInstance.setBonusWord(m_bonusWord);
    }
    if (m_featureTwentySpotIncludedSet) {
      _newInstance.setTwentySpotIncluded(m_twentySpotIncluded);
    }
    return _newInstance;
  }

  public PendingTicketDetailBuilder boardContent(java.lang.String p_boardContent) {
    m_boardContent = p_boardContent;
    m_featureBoardContentSet = true;
    return this;
  }

  public PendingTicketDetailBuilder bonusWord(java.lang.String p_bonusWord) {
    m_bonusWord = p_bonusWord;
    m_featureBonusWordSet = true;
    return this;
  }

  public PendingTicketDetailBuilder twentySpotIncluded(Boolean p_twentySpotIncluded) {
    m_twentySpotIncluded = p_twentySpotIncluded;
    m_featureTwentySpotIncludedSet = true;
    return this;
  }
}
