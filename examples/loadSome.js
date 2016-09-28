  'use strict';

  var sprintf = require('sprintf-js').sprintf;
  var app = require('../server/server.js');

  var contentStrings = [
    'cheap__work' +
    'u_p_each__e' +
    's_i_n__ahoy' +
    'tactical_d_' +
    'o___n__e_d_' +
    'majesty_e_s' +
    '__a_u_o_c_a' +
    'sew_l_n_h_n' +
    'o__landlord' +
    'also__e___a' +
    'r__g__rural',
    'LOCAL__B__SI___I__OVENQUANTIFY__AU_R_T_O_TAPI_E_L_R_I__D_A_ELEMENT_T_I__S___E_W_DIVISIONNONE__G_D_UA__ARCH_L_RPULL__THEME',
    'INPUT__S__RM___H__UNDOPENTAGON__OO_A_N_L_RUTS_I_K_F_A__E_L_SHAMPOO_P_A__C___X_A_GRATUITYTRIO__O_D_GO__NEAR_E_ETRAY__YEARN',
    'R__M__WHALEUNDO__E___NB__PARADISEYOU_V_S_D_R__S_AME_E_GSPECIAL_A_YQ___L__D_O_UNSTABLEI_A_O_B__PULLR_D_LIST__IEVADE__HARD',
    'WORM__REACHI__EDGE_C_OGIRL__C_R_N_N_OBSOLETE_K_N__M___S__A_COMMENTT_F_L_E_A__O_ABA_N_REDPERSUADE__II___S__YEARCONVEY_E__T',
    'PIANO__B__SI___U__IRONCONSTANT__ON_O_P_E_BOWI_O_U_G_U__C_N_THOUGHT_O_V__T___A_W_OFFICIALPLUG__A_D_EE__UNIT_L_NTAKE__EVENT',
    'B__E__HELLOONLY__A___RO__ECONOMICKID_R_GME_H__I_I_A_N_IHAMSTER_U_DE___I__O_T_INNOCENT_E_G_E_I__THATH_X_SIZE__ATOTEM__RAMP',
    'DREAM__KNOTE_C_OXEN__AC_H_N__AJARECONOMIC_R_N___L__K_M_TYPHOON_T_M__I_G_O_A_ALOT_U_T_X_NU__REVISIONRATE__O___EK__D__NEVER',
    'DENT__DRAINI__RACE_U_AGALA__F_N_R_N_SINISTER_Y_H__C___O__A_RAINBOWS_R_E_E_E__P_E_F_N_DOTEVALUATE__UN___S__GRABDEGREE_G__E',
    'RAISE__B__SI___N__AUTOTOGETHER__AU_U_I_V_RAPA_S_R_A_U__L_T_EXPENSE_T_S__O___N_A_CARRIAGEUNDO__A_C_RS__RAFT_H_GEDGE__EVERY',
    'C__H__AWFULALTO__R___AV__GRATUITYEGG_E_I_T_O__U_C_S_E_UCOMMENT_M_TA___P__A_C_NINETEEN_A_C_O_I__GIRLE_O_ONCE__OLINEN__LIFT',
    'H__E__SIGHTONLY__A___WM__EPILOGUEEND_A_U_U_N__I_R_T_S_TBAGGAGE_T_YO___C__S_Z_NORTHERN_O_B_I_U__AHOYO_C_TUSK__ENIECE__EXIT',
    'LATCH__SAVEO_Y_EACH__AC_P_A__EDITANECDOTE_C_T___L__R_E_EVENING_A_O__A_G_L_L_RTAR_H_O_S_AE__STUBBORNLACK__A___GL__Y__LAPSE',
    'SNAKE__B__BU___X__ACHEPOSTCARD__SP_C_U_E_LOTL_A_S_F_I__Y_N_ELEMENT_O_A__R___W_I_SIDELINEALAS__N_D_NI__EPIC_L_TDUST__EVERY',
    'ROBOT__UNDOE_U_HARP__AS_M_E__SACKOPPOSITE_A_R___A__T_B_TRIBUTE_V_S__C_R_X_E_CUSE_U_C_R_RN__ASSEMBLEIRIS__E___ET__K__DOZEN',
    'P__T__SORRYALSO__U___EV__WINDFALLEAT_N_D_L_L__W_T_E_O_OPROTEIN_E_WO___R__S_T_OUTBREAK_E_D_A_U__AJARL_X_PLOT__UEXIST__EVEN',
    'CIDER__SOLOO_A_EAST__WS_W_G__OXENMANEUVER_N_I___L__Y_D_COMMAND_F_A__O_T_O_L_PDUB_O_M_A_PIE_TRIANGLERAKE__I___AE__A__NEVER',
    'YIELD__SUCHO_P_EACH__ON_I_T__RINGDECREASE_O_E___C__D_D_RECITAL_B_S__A_I_A_O_ARAPEV_D_W_LI__PENDULUMCASE_AE__WOH__N__ROBIN'
  ];

  var FiveXTicket = app.models.FiveXTicket;

  var seriesBase = 1057686;
  var sequenceNum = 12;
  var ii = 0;
  var seqDelta = 3;
  var seriesDelta = 25;
  var seriesMod = 100;
  var seriesNum;

var content;
  for (content in contentStrings) {
    seriesNum = seriesBase + ii;
    FiveXTicket.create({
      ticketId: sprintf('%d-%03d', seriesNum, sequenceNum),
      gridContent: content,
      yourLetters: 'ABRTEH____________',
      finalPayout: -1,
      fastValue: 0,
      multiValue: 0
    }).then(console.log);
    ii = (ii + seriesDelta) % seriesMod;
    sequenceNum += seqDelta;
  }
