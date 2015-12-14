package info.jchein.sandbox.app.crossword.resources.xw.util.builder;

/**
 * <!-- begin-user-doc --> 
 *   A builder for the model object '<em><b>info.jchein.sandbox.app.crossword.resources.xw.PublishedTicketDetail</b></em>'.
 * <!-- end-user-doc -->
 * 
 * @generated
 */
public class PublishedTicketDetailBuilder implements info.jchein.sandbox.app.crossword.resources.xw.util.builder.IXwBuilder<info.jchein.sandbox.app.crossword.resources.xw.PublishedTicketDetail> {
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
   * @see #newPublishedTicketDetailBuilder()
   */
  private PublishedTicketDetailBuilder() {
  }

  /**
   * This method creates a new instance of the PublishedTicketDetailBuilder.
   * @return new instance of the PublishedTicketDetailBuilder
   */
  public static PublishedTicketDetailBuilder newPublishedTicketDetailBuilder() {
    return new PublishedTicketDetailBuilder();
  }

  /**
   * This method creates a new instance of the PublishedTicketDetailBuilder. 
   * The builder is initialized using an existing '<em><b>info.jchein.sandbox.app.crossword.resources.xw.PublishedTicketDetail</b></em>' model object.
   * In order to avoid changes to the provided '<em><b>info.jchein.sandbox.app.crossword.resources.xw.PublishedTicketDetail</b></em>' model object, a copy is created using <em><b>org.eclipse.emf.ecore.util.EcoreUtil.Copier</b></em>.
   * @param publishedTicketDetail The existing '<em><b>info.jchein.sandbox.app.crossword.resources.xw.PublishedTicketDetail</b></em>' model object to be used for the initialization of the builder  
   * @return new initialized instance of the PublishedTicketDetailBuilder
   */
  public static PublishedTicketDetailBuilder newPublishedTicketDetailBuilder(info.jchein.sandbox.app.crossword.resources.xw.PublishedTicketDetail p_publishedTicketDetail) {
    org.eclipse.emf.ecore.util.EcoreUtil.Copier c = new org.eclipse.emf.ecore.util.EcoreUtil.Copier();
    info.jchein.sandbox.app.crossword.resources.xw.PublishedTicketDetail _publishedTicketDetail = (info.jchein.sandbox.app.crossword.resources.xw.PublishedTicketDetail) c
        .copy(((info.jchein.sandbox.app.crossword.resources.xw.PublishedTicketDetail) p_publishedTicketDetail));
    c.copyReferences();
    PublishedTicketDetailBuilder _builder = newPublishedTicketDetailBuilder();
    _builder.boardContent(_publishedTicketDetail.getBoardContent());
    _builder.bonusWord(_publishedTicketDetail.getBonusWord());
    _builder.twentySpotIncluded(_publishedTicketDetail.isTwentySpotIncluded());
    return _builder;
  }

  /**
   * This method can be used to override attributes of the builder. It constructs a new builder and copies the current values to it.
   */
  public PublishedTicketDetailBuilder but() {
    PublishedTicketDetailBuilder _builder = newPublishedTicketDetailBuilder();
    _builder.m_featureBoardContentSet = m_featureBoardContentSet;
    _builder.m_boardContent = m_boardContent;
    _builder.m_featureBonusWordSet = m_featureBonusWordSet;
    _builder.m_bonusWord = m_bonusWord;
    _builder.m_featureTwentySpotIncludedSet = m_featureTwentySpotIncludedSet;
    _builder.m_twentySpotIncluded = m_twentySpotIncluded;
    return _builder;
  }

  /**
   * This method constructs the final info.jchein.sandbox.app.crossword.resources.xw.PublishedTicketDetail type.
   * @return new instance of the info.jchein.sandbox.app.crossword.resources.xw.PublishedTicketDetail type
   */
  public info.jchein.sandbox.app.crossword.resources.xw.PublishedTicketDetail build() {
    final info.jchein.sandbox.app.crossword.resources.xw.PublishedTicketDetail _newInstance = (info.jchein.sandbox.app.crossword.resources.xw.PublishedTicketDetail) info.jchein.sandbox.app.crossword.resources.xw.XwFactory.eINSTANCE
        .create(info.jchein.sandbox.app.crossword.resources.xw.XwPackage.eINSTANCE.getPublishedTicketDetail());
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

  public PublishedTicketDetailBuilder boardContent(java.lang.String p_boardContent) {
    m_boardContent = p_boardContent;
    m_featureBoardContentSet = true;
    return this;
  }

  public PublishedTicketDetailBuilder bonusWord(java.lang.String p_bonusWord) {
    m_bonusWord = p_bonusWord;
    m_featureBonusWordSet = true;
    return this;
  }

  public PublishedTicketDetailBuilder twentySpotIncluded(Boolean p_twentySpotIncluded) {
    m_twentySpotIncluded = p_twentySpotIncluded;
    m_featureTwentySpotIncludedSet = true;
    return this;
  }
}
