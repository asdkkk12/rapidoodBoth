package com.rm2pt.rapidood.cd.parser.antlr.internal;

import org.eclipse.xtext.*;
import org.eclipse.xtext.parser.*;
import org.eclipse.xtext.parser.impl.*;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.parser.antlr.AbstractInternalAntlrParser;
import org.eclipse.xtext.parser.antlr.XtextTokenStream;
import org.eclipse.xtext.parser.antlr.XtextTokenStream.HiddenTokens;
import org.eclipse.xtext.parser.antlr.AntlrDatatypeRuleToken;
import com.rm2pt.rapidood.cd.services.ClassDiagramGrammarAccess;



import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
@SuppressWarnings("all")
public class InternalClassDiagramParser extends AbstractInternalAntlrParser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "RULE_ID", "RULE_DOUBLE_QUOTED_STRING", "RULE_ESCAPED_CHARACTER", "RULE_INT", "RULE_STRING", "RULE_ML_COMMENT", "RULE_SL_COMMENT", "RULE_WS", "RULE_ANY_OTHER", "'@startuml'", "'@enduml'", "'package'", "'{'", "'}'", "'.'", "'class'", "'interface'", "'abstract'", "'{static}'", "':'", "'{abstract}'", "'('", "','", "')'", "'+'", "'-'", "'#'", "'~'", "'<|'", "'|>'", "'o'", "'*'", "'<'", "'>'", "'--'", "'..'", "'Boolean'", "'Integer'", "'Real'", "'String'", "'Double'", "'UnlimitedNatural'", "'Date'", "'void'", "'int'", "'boolean'", "'double'", "'enum'"
    };
    public static final int T__50=50;
    public static final int T__19=19;
    public static final int T__15=15;
    public static final int T__16=16;
    public static final int T__17=17;
    public static final int T__18=18;
    public static final int RULE_ESCAPED_CHARACTER=6;
    public static final int T__13=13;
    public static final int T__14=14;
    public static final int T__51=51;
    public static final int RULE_ID=4;
    public static final int T__26=26;
    public static final int T__27=27;
    public static final int T__28=28;
    public static final int RULE_INT=7;
    public static final int T__29=29;
    public static final int T__22=22;
    public static final int RULE_ML_COMMENT=9;
    public static final int T__23=23;
    public static final int T__24=24;
    public static final int T__25=25;
    public static final int T__20=20;
    public static final int T__21=21;
    public static final int RULE_STRING=8;
    public static final int RULE_SL_COMMENT=10;
    public static final int T__37=37;
    public static final int T__38=38;
    public static final int T__39=39;
    public static final int T__33=33;
    public static final int T__34=34;
    public static final int T__35=35;
    public static final int T__36=36;
    public static final int EOF=-1;
    public static final int T__30=30;
    public static final int T__31=31;
    public static final int T__32=32;
    public static final int RULE_WS=11;
    public static final int RULE_ANY_OTHER=12;
    public static final int T__48=48;
    public static final int T__49=49;
    public static final int RULE_DOUBLE_QUOTED_STRING=5;
    public static final int T__44=44;
    public static final int T__45=45;
    public static final int T__46=46;
    public static final int T__47=47;
    public static final int T__40=40;
    public static final int T__41=41;
    public static final int T__42=42;
    public static final int T__43=43;

    // delegates
    // delegators


        public InternalClassDiagramParser(TokenStream input) {
            this(input, new RecognizerSharedState());
        }
        public InternalClassDiagramParser(TokenStream input, RecognizerSharedState state) {
            super(input, state);
             
        }
        

    public String[] getTokenNames() { return InternalClassDiagramParser.tokenNames; }
    public String getGrammarFileName() { return "InternalClassDiagram.g"; }



     	private ClassDiagramGrammarAccess grammarAccess;

        public InternalClassDiagramParser(TokenStream input, ClassDiagramGrammarAccess grammarAccess) {
            this(input);
            this.grammarAccess = grammarAccess;
            registerRules(grammarAccess.getGrammar());
        }

        @Override
        protected String getFirstRuleName() {
        	return "ClassDiagram";
       	}

       	@Override
       	protected ClassDiagramGrammarAccess getGrammarAccess() {
       		return grammarAccess;
       	}




    // $ANTLR start "entryRuleClassDiagram"
    // InternalClassDiagram.g:64:1: entryRuleClassDiagram returns [EObject current=null] : iv_ruleClassDiagram= ruleClassDiagram EOF ;
    public final EObject entryRuleClassDiagram() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleClassDiagram = null;


        try {
            // InternalClassDiagram.g:64:53: (iv_ruleClassDiagram= ruleClassDiagram EOF )
            // InternalClassDiagram.g:65:2: iv_ruleClassDiagram= ruleClassDiagram EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getClassDiagramRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleClassDiagram=ruleClassDiagram();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleClassDiagram; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleClassDiagram"


    // $ANTLR start "ruleClassDiagram"
    // InternalClassDiagram.g:71:1: ruleClassDiagram returns [EObject current=null] : ( () otherlv_1= '@startuml' ( (lv_elements_2_0= ruleAbstractElement ) )* otherlv_3= '@enduml' ) ;
    public final EObject ruleClassDiagram() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_3=null;
        EObject lv_elements_2_0 = null;



        	enterRule();

        try {
            // InternalClassDiagram.g:77:2: ( ( () otherlv_1= '@startuml' ( (lv_elements_2_0= ruleAbstractElement ) )* otherlv_3= '@enduml' ) )
            // InternalClassDiagram.g:78:2: ( () otherlv_1= '@startuml' ( (lv_elements_2_0= ruleAbstractElement ) )* otherlv_3= '@enduml' )
            {
            // InternalClassDiagram.g:78:2: ( () otherlv_1= '@startuml' ( (lv_elements_2_0= ruleAbstractElement ) )* otherlv_3= '@enduml' )
            // InternalClassDiagram.g:79:3: () otherlv_1= '@startuml' ( (lv_elements_2_0= ruleAbstractElement ) )* otherlv_3= '@enduml'
            {
            // InternalClassDiagram.g:79:3: ()
            // InternalClassDiagram.g:80:4: 
            {
            if ( state.backtracking==0 ) {

              				current = forceCreateModelElement(
              					grammarAccess.getClassDiagramAccess().getClassDiagramAction_0(),
              					current);
              			
            }

            }

            otherlv_1=(Token)match(input,13,FOLLOW_3); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_1, grammarAccess.getClassDiagramAccess().getStartumlKeyword_1());
              		
            }
            // InternalClassDiagram.g:90:3: ( (lv_elements_2_0= ruleAbstractElement ) )*
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( (LA1_0==RULE_ID||LA1_0==15||(LA1_0>=19 && LA1_0<=21)||LA1_0==51) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // InternalClassDiagram.g:91:4: (lv_elements_2_0= ruleAbstractElement )
            	    {
            	    // InternalClassDiagram.g:91:4: (lv_elements_2_0= ruleAbstractElement )
            	    // InternalClassDiagram.g:92:5: lv_elements_2_0= ruleAbstractElement
            	    {
            	    if ( state.backtracking==0 ) {

            	      					newCompositeNode(grammarAccess.getClassDiagramAccess().getElementsAbstractElementParserRuleCall_2_0());
            	      				
            	    }
            	    pushFollow(FOLLOW_3);
            	    lv_elements_2_0=ruleAbstractElement();

            	    state._fsp--;
            	    if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      					if (current==null) {
            	      						current = createModelElementForParent(grammarAccess.getClassDiagramRule());
            	      					}
            	      					add(
            	      						current,
            	      						"elements",
            	      						lv_elements_2_0,
            	      						"com.rm2pt.rapidood.cd.ClassDiagram.AbstractElement");
            	      					afterParserOrEnumRuleCall();
            	      				
            	    }

            	    }


            	    }
            	    break;

            	default :
            	    break loop1;
                }
            } while (true);

            otherlv_3=(Token)match(input,14,FOLLOW_2); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_3, grammarAccess.getClassDiagramAccess().getEndumlKeyword_3());
              		
            }

            }


            }

            if ( state.backtracking==0 ) {

              	leaveRule();

            }
        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleClassDiagram"


    // $ANTLR start "entryRuleAbstractElement"
    // InternalClassDiagram.g:117:1: entryRuleAbstractElement returns [EObject current=null] : iv_ruleAbstractElement= ruleAbstractElement EOF ;
    public final EObject entryRuleAbstractElement() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleAbstractElement = null;


        try {
            // InternalClassDiagram.g:117:56: (iv_ruleAbstractElement= ruleAbstractElement EOF )
            // InternalClassDiagram.g:118:2: iv_ruleAbstractElement= ruleAbstractElement EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getAbstractElementRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleAbstractElement=ruleAbstractElement();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleAbstractElement; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleAbstractElement"


    // $ANTLR start "ruleAbstractElement"
    // InternalClassDiagram.g:124:1: ruleAbstractElement returns [EObject current=null] : (this_PackageDeclaration_0= rulePackageDeclaration | this_Class_1= ruleClass | this_Interface_2= ruleInterface | this_AbstractClass_3= ruleAbstractClass | this_Relationship_4= ruleRelationship | this_Enum_5= ruleEnum ) ;
    public final EObject ruleAbstractElement() throws RecognitionException {
        EObject current = null;

        EObject this_PackageDeclaration_0 = null;

        EObject this_Class_1 = null;

        EObject this_Interface_2 = null;

        EObject this_AbstractClass_3 = null;

        EObject this_Relationship_4 = null;

        EObject this_Enum_5 = null;



        	enterRule();

        try {
            // InternalClassDiagram.g:130:2: ( (this_PackageDeclaration_0= rulePackageDeclaration | this_Class_1= ruleClass | this_Interface_2= ruleInterface | this_AbstractClass_3= ruleAbstractClass | this_Relationship_4= ruleRelationship | this_Enum_5= ruleEnum ) )
            // InternalClassDiagram.g:131:2: (this_PackageDeclaration_0= rulePackageDeclaration | this_Class_1= ruleClass | this_Interface_2= ruleInterface | this_AbstractClass_3= ruleAbstractClass | this_Relationship_4= ruleRelationship | this_Enum_5= ruleEnum )
            {
            // InternalClassDiagram.g:131:2: (this_PackageDeclaration_0= rulePackageDeclaration | this_Class_1= ruleClass | this_Interface_2= ruleInterface | this_AbstractClass_3= ruleAbstractClass | this_Relationship_4= ruleRelationship | this_Enum_5= ruleEnum )
            int alt2=6;
            switch ( input.LA(1) ) {
            case 15:
                {
                alt2=1;
                }
                break;
            case 19:
                {
                alt2=2;
                }
                break;
            case 20:
                {
                alt2=3;
                }
                break;
            case 21:
                {
                alt2=4;
                }
                break;
            case RULE_ID:
                {
                alt2=5;
                }
                break;
            case 51:
                {
                alt2=6;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 2, 0, input);

                throw nvae;
            }

            switch (alt2) {
                case 1 :
                    // InternalClassDiagram.g:132:3: this_PackageDeclaration_0= rulePackageDeclaration
                    {
                    if ( state.backtracking==0 ) {

                      			newCompositeNode(grammarAccess.getAbstractElementAccess().getPackageDeclarationParserRuleCall_0());
                      		
                    }
                    pushFollow(FOLLOW_2);
                    this_PackageDeclaration_0=rulePackageDeclaration();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current = this_PackageDeclaration_0;
                      			afterParserOrEnumRuleCall();
                      		
                    }

                    }
                    break;
                case 2 :
                    // InternalClassDiagram.g:141:3: this_Class_1= ruleClass
                    {
                    if ( state.backtracking==0 ) {

                      			newCompositeNode(grammarAccess.getAbstractElementAccess().getClassParserRuleCall_1());
                      		
                    }
                    pushFollow(FOLLOW_2);
                    this_Class_1=ruleClass();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current = this_Class_1;
                      			afterParserOrEnumRuleCall();
                      		
                    }

                    }
                    break;
                case 3 :
                    // InternalClassDiagram.g:150:3: this_Interface_2= ruleInterface
                    {
                    if ( state.backtracking==0 ) {

                      			newCompositeNode(grammarAccess.getAbstractElementAccess().getInterfaceParserRuleCall_2());
                      		
                    }
                    pushFollow(FOLLOW_2);
                    this_Interface_2=ruleInterface();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current = this_Interface_2;
                      			afterParserOrEnumRuleCall();
                      		
                    }

                    }
                    break;
                case 4 :
                    // InternalClassDiagram.g:159:3: this_AbstractClass_3= ruleAbstractClass
                    {
                    if ( state.backtracking==0 ) {

                      			newCompositeNode(grammarAccess.getAbstractElementAccess().getAbstractClassParserRuleCall_3());
                      		
                    }
                    pushFollow(FOLLOW_2);
                    this_AbstractClass_3=ruleAbstractClass();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current = this_AbstractClass_3;
                      			afterParserOrEnumRuleCall();
                      		
                    }

                    }
                    break;
                case 5 :
                    // InternalClassDiagram.g:168:3: this_Relationship_4= ruleRelationship
                    {
                    if ( state.backtracking==0 ) {

                      			newCompositeNode(grammarAccess.getAbstractElementAccess().getRelationshipParserRuleCall_4());
                      		
                    }
                    pushFollow(FOLLOW_2);
                    this_Relationship_4=ruleRelationship();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current = this_Relationship_4;
                      			afterParserOrEnumRuleCall();
                      		
                    }

                    }
                    break;
                case 6 :
                    // InternalClassDiagram.g:177:3: this_Enum_5= ruleEnum
                    {
                    if ( state.backtracking==0 ) {

                      			newCompositeNode(grammarAccess.getAbstractElementAccess().getEnumParserRuleCall_5());
                      		
                    }
                    pushFollow(FOLLOW_2);
                    this_Enum_5=ruleEnum();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current = this_Enum_5;
                      			afterParserOrEnumRuleCall();
                      		
                    }

                    }
                    break;

            }


            }

            if ( state.backtracking==0 ) {

              	leaveRule();

            }
        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleAbstractElement"


    // $ANTLR start "entryRulePackageDeclaration"
    // InternalClassDiagram.g:189:1: entryRulePackageDeclaration returns [EObject current=null] : iv_rulePackageDeclaration= rulePackageDeclaration EOF ;
    public final EObject entryRulePackageDeclaration() throws RecognitionException {
        EObject current = null;

        EObject iv_rulePackageDeclaration = null;


        try {
            // InternalClassDiagram.g:189:59: (iv_rulePackageDeclaration= rulePackageDeclaration EOF )
            // InternalClassDiagram.g:190:2: iv_rulePackageDeclaration= rulePackageDeclaration EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getPackageDeclarationRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_rulePackageDeclaration=rulePackageDeclaration();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_rulePackageDeclaration; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRulePackageDeclaration"


    // $ANTLR start "rulePackageDeclaration"
    // InternalClassDiagram.g:196:1: rulePackageDeclaration returns [EObject current=null] : (otherlv_0= 'package' ( (lv_name_1_0= ruleFQN ) ) otherlv_2= '{' ( (lv_elements_3_0= ruleAbstractElement ) )* otherlv_4= '}' ) ;
    public final EObject rulePackageDeclaration() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        AntlrDatatypeRuleToken lv_name_1_0 = null;

        EObject lv_elements_3_0 = null;



        	enterRule();

        try {
            // InternalClassDiagram.g:202:2: ( (otherlv_0= 'package' ( (lv_name_1_0= ruleFQN ) ) otherlv_2= '{' ( (lv_elements_3_0= ruleAbstractElement ) )* otherlv_4= '}' ) )
            // InternalClassDiagram.g:203:2: (otherlv_0= 'package' ( (lv_name_1_0= ruleFQN ) ) otherlv_2= '{' ( (lv_elements_3_0= ruleAbstractElement ) )* otherlv_4= '}' )
            {
            // InternalClassDiagram.g:203:2: (otherlv_0= 'package' ( (lv_name_1_0= ruleFQN ) ) otherlv_2= '{' ( (lv_elements_3_0= ruleAbstractElement ) )* otherlv_4= '}' )
            // InternalClassDiagram.g:204:3: otherlv_0= 'package' ( (lv_name_1_0= ruleFQN ) ) otherlv_2= '{' ( (lv_elements_3_0= ruleAbstractElement ) )* otherlv_4= '}'
            {
            otherlv_0=(Token)match(input,15,FOLLOW_4); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_0, grammarAccess.getPackageDeclarationAccess().getPackageKeyword_0());
              		
            }
            // InternalClassDiagram.g:208:3: ( (lv_name_1_0= ruleFQN ) )
            // InternalClassDiagram.g:209:4: (lv_name_1_0= ruleFQN )
            {
            // InternalClassDiagram.g:209:4: (lv_name_1_0= ruleFQN )
            // InternalClassDiagram.g:210:5: lv_name_1_0= ruleFQN
            {
            if ( state.backtracking==0 ) {

              					newCompositeNode(grammarAccess.getPackageDeclarationAccess().getNameFQNParserRuleCall_1_0());
              				
            }
            pushFollow(FOLLOW_5);
            lv_name_1_0=ruleFQN();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              					if (current==null) {
              						current = createModelElementForParent(grammarAccess.getPackageDeclarationRule());
              					}
              					set(
              						current,
              						"name",
              						lv_name_1_0,
              						"com.rm2pt.rapidood.cd.ClassDiagram.FQN");
              					afterParserOrEnumRuleCall();
              				
            }

            }


            }

            otherlv_2=(Token)match(input,16,FOLLOW_6); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_2, grammarAccess.getPackageDeclarationAccess().getLeftCurlyBracketKeyword_2());
              		
            }
            // InternalClassDiagram.g:231:3: ( (lv_elements_3_0= ruleAbstractElement ) )*
            loop3:
            do {
                int alt3=2;
                int LA3_0 = input.LA(1);

                if ( (LA3_0==RULE_ID||LA3_0==15||(LA3_0>=19 && LA3_0<=21)||LA3_0==51) ) {
                    alt3=1;
                }


                switch (alt3) {
            	case 1 :
            	    // InternalClassDiagram.g:232:4: (lv_elements_3_0= ruleAbstractElement )
            	    {
            	    // InternalClassDiagram.g:232:4: (lv_elements_3_0= ruleAbstractElement )
            	    // InternalClassDiagram.g:233:5: lv_elements_3_0= ruleAbstractElement
            	    {
            	    if ( state.backtracking==0 ) {

            	      					newCompositeNode(grammarAccess.getPackageDeclarationAccess().getElementsAbstractElementParserRuleCall_3_0());
            	      				
            	    }
            	    pushFollow(FOLLOW_6);
            	    lv_elements_3_0=ruleAbstractElement();

            	    state._fsp--;
            	    if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      					if (current==null) {
            	      						current = createModelElementForParent(grammarAccess.getPackageDeclarationRule());
            	      					}
            	      					add(
            	      						current,
            	      						"elements",
            	      						lv_elements_3_0,
            	      						"com.rm2pt.rapidood.cd.ClassDiagram.AbstractElement");
            	      					afterParserOrEnumRuleCall();
            	      				
            	    }

            	    }


            	    }
            	    break;

            	default :
            	    break loop3;
                }
            } while (true);

            otherlv_4=(Token)match(input,17,FOLLOW_2); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_4, grammarAccess.getPackageDeclarationAccess().getRightCurlyBracketKeyword_4());
              		
            }

            }


            }

            if ( state.backtracking==0 ) {

              	leaveRule();

            }
        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "rulePackageDeclaration"


    // $ANTLR start "entryRuleFQN"
    // InternalClassDiagram.g:258:1: entryRuleFQN returns [String current=null] : iv_ruleFQN= ruleFQN EOF ;
    public final String entryRuleFQN() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleFQN = null;


        try {
            // InternalClassDiagram.g:258:43: (iv_ruleFQN= ruleFQN EOF )
            // InternalClassDiagram.g:259:2: iv_ruleFQN= ruleFQN EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getFQNRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleFQN=ruleFQN();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleFQN.getText(); 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleFQN"


    // $ANTLR start "ruleFQN"
    // InternalClassDiagram.g:265:1: ruleFQN returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (this_ID_0= RULE_ID ( ( ( '.' )=>kw= '.' ) this_ID_2= RULE_ID )* ) ;
    public final AntlrDatatypeRuleToken ruleFQN() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_ID_0=null;
        Token kw=null;
        Token this_ID_2=null;


        	enterRule();

        try {
            // InternalClassDiagram.g:271:2: ( (this_ID_0= RULE_ID ( ( ( '.' )=>kw= '.' ) this_ID_2= RULE_ID )* ) )
            // InternalClassDiagram.g:272:2: (this_ID_0= RULE_ID ( ( ( '.' )=>kw= '.' ) this_ID_2= RULE_ID )* )
            {
            // InternalClassDiagram.g:272:2: (this_ID_0= RULE_ID ( ( ( '.' )=>kw= '.' ) this_ID_2= RULE_ID )* )
            // InternalClassDiagram.g:273:3: this_ID_0= RULE_ID ( ( ( '.' )=>kw= '.' ) this_ID_2= RULE_ID )*
            {
            this_ID_0=(Token)match(input,RULE_ID,FOLLOW_7); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			current.merge(this_ID_0);
              		
            }
            if ( state.backtracking==0 ) {

              			newLeafNode(this_ID_0, grammarAccess.getFQNAccess().getIDTerminalRuleCall_0());
              		
            }
            // InternalClassDiagram.g:280:3: ( ( ( '.' )=>kw= '.' ) this_ID_2= RULE_ID )*
            loop4:
            do {
                int alt4=2;
                int LA4_0 = input.LA(1);

                if ( (LA4_0==18) ) {
                    int LA4_2 = input.LA(2);

                    if ( (LA4_2==RULE_ID) ) {
                        int LA4_3 = input.LA(3);

                        if ( (synpred1_InternalClassDiagram()) ) {
                            alt4=1;
                        }


                    }


                }


                switch (alt4) {
            	case 1 :
            	    // InternalClassDiagram.g:281:4: ( ( '.' )=>kw= '.' ) this_ID_2= RULE_ID
            	    {
            	    // InternalClassDiagram.g:281:4: ( ( '.' )=>kw= '.' )
            	    // InternalClassDiagram.g:282:5: ( '.' )=>kw= '.'
            	    {
            	    kw=(Token)match(input,18,FOLLOW_4); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      					current.merge(kw);
            	      					newLeafNode(kw, grammarAccess.getFQNAccess().getFullStopKeyword_1_0());
            	      				
            	    }

            	    }

            	    this_ID_2=(Token)match(input,RULE_ID,FOLLOW_7); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      				current.merge(this_ID_2);
            	      			
            	    }
            	    if ( state.backtracking==0 ) {

            	      				newLeafNode(this_ID_2, grammarAccess.getFQNAccess().getIDTerminalRuleCall_1_1());
            	      			
            	    }

            	    }
            	    break;

            	default :
            	    break loop4;
                }
            } while (true);


            }


            }

            if ( state.backtracking==0 ) {

              	leaveRule();

            }
        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleFQN"


    // $ANTLR start "entryRuleClass"
    // InternalClassDiagram.g:301:1: entryRuleClass returns [EObject current=null] : iv_ruleClass= ruleClass EOF ;
    public final EObject entryRuleClass() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleClass = null;


        try {
            // InternalClassDiagram.g:301:46: (iv_ruleClass= ruleClass EOF )
            // InternalClassDiagram.g:302:2: iv_ruleClass= ruleClass EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getClassRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleClass=ruleClass();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleClass; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleClass"


    // $ANTLR start "ruleClass"
    // InternalClassDiagram.g:308:1: ruleClass returns [EObject current=null] : (otherlv_0= 'class' ( (lv_name_1_0= ruleSimpleName ) ) otherlv_2= '{' ( (lv_attributes_3_0= ruleAttribute ) )* ( (lv_operations_4_0= ruleOperation ) )* otherlv_5= '}' ) ;
    public final EObject ruleClass() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_2=null;
        Token otherlv_5=null;
        AntlrDatatypeRuleToken lv_name_1_0 = null;

        EObject lv_attributes_3_0 = null;

        EObject lv_operations_4_0 = null;



        	enterRule();

        try {
            // InternalClassDiagram.g:314:2: ( (otherlv_0= 'class' ( (lv_name_1_0= ruleSimpleName ) ) otherlv_2= '{' ( (lv_attributes_3_0= ruleAttribute ) )* ( (lv_operations_4_0= ruleOperation ) )* otherlv_5= '}' ) )
            // InternalClassDiagram.g:315:2: (otherlv_0= 'class' ( (lv_name_1_0= ruleSimpleName ) ) otherlv_2= '{' ( (lv_attributes_3_0= ruleAttribute ) )* ( (lv_operations_4_0= ruleOperation ) )* otherlv_5= '}' )
            {
            // InternalClassDiagram.g:315:2: (otherlv_0= 'class' ( (lv_name_1_0= ruleSimpleName ) ) otherlv_2= '{' ( (lv_attributes_3_0= ruleAttribute ) )* ( (lv_operations_4_0= ruleOperation ) )* otherlv_5= '}' )
            // InternalClassDiagram.g:316:3: otherlv_0= 'class' ( (lv_name_1_0= ruleSimpleName ) ) otherlv_2= '{' ( (lv_attributes_3_0= ruleAttribute ) )* ( (lv_operations_4_0= ruleOperation ) )* otherlv_5= '}'
            {
            otherlv_0=(Token)match(input,19,FOLLOW_4); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_0, grammarAccess.getClassAccess().getClassKeyword_0());
              		
            }
            // InternalClassDiagram.g:320:3: ( (lv_name_1_0= ruleSimpleName ) )
            // InternalClassDiagram.g:321:4: (lv_name_1_0= ruleSimpleName )
            {
            // InternalClassDiagram.g:321:4: (lv_name_1_0= ruleSimpleName )
            // InternalClassDiagram.g:322:5: lv_name_1_0= ruleSimpleName
            {
            if ( state.backtracking==0 ) {

              					newCompositeNode(grammarAccess.getClassAccess().getNameSimpleNameParserRuleCall_1_0());
              				
            }
            pushFollow(FOLLOW_5);
            lv_name_1_0=ruleSimpleName();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              					if (current==null) {
              						current = createModelElementForParent(grammarAccess.getClassRule());
              					}
              					set(
              						current,
              						"name",
              						lv_name_1_0,
              						"com.rm2pt.rapidood.cd.ClassDiagram.SimpleName");
              					afterParserOrEnumRuleCall();
              				
            }

            }


            }

            otherlv_2=(Token)match(input,16,FOLLOW_8); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_2, grammarAccess.getClassAccess().getLeftCurlyBracketKeyword_2());
              		
            }
            // InternalClassDiagram.g:343:3: ( (lv_attributes_3_0= ruleAttribute ) )*
            loop5:
            do {
                int alt5=2;
                switch ( input.LA(1) ) {
                case 28:
                    {
                    int LA5_1 = input.LA(2);

                    if ( (LA5_1==22) ) {
                        int LA5_6 = input.LA(3);

                        if ( (LA5_6==RULE_ID) ) {
                            int LA5_7 = input.LA(4);

                            if ( (LA5_7==23) ) {
                                alt5=1;
                            }


                        }


                    }
                    else if ( (LA5_1==RULE_ID) ) {
                        int LA5_7 = input.LA(3);

                        if ( (LA5_7==23) ) {
                            alt5=1;
                        }


                    }


                    }
                    break;
                case 29:
                    {
                    int LA5_2 = input.LA(2);

                    if ( (LA5_2==22) ) {
                        int LA5_6 = input.LA(3);

                        if ( (LA5_6==RULE_ID) ) {
                            int LA5_7 = input.LA(4);

                            if ( (LA5_7==23) ) {
                                alt5=1;
                            }


                        }


                    }
                    else if ( (LA5_2==RULE_ID) ) {
                        int LA5_7 = input.LA(3);

                        if ( (LA5_7==23) ) {
                            alt5=1;
                        }


                    }


                    }
                    break;
                case 30:
                    {
                    int LA5_3 = input.LA(2);

                    if ( (LA5_3==22) ) {
                        int LA5_6 = input.LA(3);

                        if ( (LA5_6==RULE_ID) ) {
                            int LA5_7 = input.LA(4);

                            if ( (LA5_7==23) ) {
                                alt5=1;
                            }


                        }


                    }
                    else if ( (LA5_3==RULE_ID) ) {
                        int LA5_7 = input.LA(3);

                        if ( (LA5_7==23) ) {
                            alt5=1;
                        }


                    }


                    }
                    break;
                case 31:
                    {
                    int LA5_4 = input.LA(2);

                    if ( (LA5_4==22) ) {
                        int LA5_6 = input.LA(3);

                        if ( (LA5_6==RULE_ID) ) {
                            int LA5_7 = input.LA(4);

                            if ( (LA5_7==23) ) {
                                alt5=1;
                            }


                        }


                    }
                    else if ( (LA5_4==RULE_ID) ) {
                        int LA5_7 = input.LA(3);

                        if ( (LA5_7==23) ) {
                            alt5=1;
                        }


                    }


                    }
                    break;
                case 22:
                    {
                    int LA5_6 = input.LA(2);

                    if ( (LA5_6==RULE_ID) ) {
                        int LA5_7 = input.LA(3);

                        if ( (LA5_7==23) ) {
                            alt5=1;
                        }


                    }


                    }
                    break;
                case RULE_ID:
                    {
                    int LA5_7 = input.LA(2);

                    if ( (LA5_7==23) ) {
                        alt5=1;
                    }


                    }
                    break;

                }

                switch (alt5) {
            	case 1 :
            	    // InternalClassDiagram.g:344:4: (lv_attributes_3_0= ruleAttribute )
            	    {
            	    // InternalClassDiagram.g:344:4: (lv_attributes_3_0= ruleAttribute )
            	    // InternalClassDiagram.g:345:5: lv_attributes_3_0= ruleAttribute
            	    {
            	    if ( state.backtracking==0 ) {

            	      					newCompositeNode(grammarAccess.getClassAccess().getAttributesAttributeParserRuleCall_3_0());
            	      				
            	    }
            	    pushFollow(FOLLOW_8);
            	    lv_attributes_3_0=ruleAttribute();

            	    state._fsp--;
            	    if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      					if (current==null) {
            	      						current = createModelElementForParent(grammarAccess.getClassRule());
            	      					}
            	      					add(
            	      						current,
            	      						"attributes",
            	      						lv_attributes_3_0,
            	      						"com.rm2pt.rapidood.cd.ClassDiagram.Attribute");
            	      					afterParserOrEnumRuleCall();
            	      				
            	    }

            	    }


            	    }
            	    break;

            	default :
            	    break loop5;
                }
            } while (true);

            // InternalClassDiagram.g:362:3: ( (lv_operations_4_0= ruleOperation ) )*
            loop6:
            do {
                int alt6=2;
                int LA6_0 = input.LA(1);

                if ( (LA6_0==RULE_ID||LA6_0==22||LA6_0==24||(LA6_0>=28 && LA6_0<=31)) ) {
                    alt6=1;
                }


                switch (alt6) {
            	case 1 :
            	    // InternalClassDiagram.g:363:4: (lv_operations_4_0= ruleOperation )
            	    {
            	    // InternalClassDiagram.g:363:4: (lv_operations_4_0= ruleOperation )
            	    // InternalClassDiagram.g:364:5: lv_operations_4_0= ruleOperation
            	    {
            	    if ( state.backtracking==0 ) {

            	      					newCompositeNode(grammarAccess.getClassAccess().getOperationsOperationParserRuleCall_4_0());
            	      				
            	    }
            	    pushFollow(FOLLOW_8);
            	    lv_operations_4_0=ruleOperation();

            	    state._fsp--;
            	    if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      					if (current==null) {
            	      						current = createModelElementForParent(grammarAccess.getClassRule());
            	      					}
            	      					add(
            	      						current,
            	      						"operations",
            	      						lv_operations_4_0,
            	      						"com.rm2pt.rapidood.cd.ClassDiagram.Operation");
            	      					afterParserOrEnumRuleCall();
            	      				
            	    }

            	    }


            	    }
            	    break;

            	default :
            	    break loop6;
                }
            } while (true);

            otherlv_5=(Token)match(input,17,FOLLOW_2); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_5, grammarAccess.getClassAccess().getRightCurlyBracketKeyword_5());
              		
            }

            }


            }

            if ( state.backtracking==0 ) {

              	leaveRule();

            }
        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleClass"


    // $ANTLR start "entryRuleInterface"
    // InternalClassDiagram.g:389:1: entryRuleInterface returns [EObject current=null] : iv_ruleInterface= ruleInterface EOF ;
    public final EObject entryRuleInterface() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleInterface = null;


        try {
            // InternalClassDiagram.g:389:50: (iv_ruleInterface= ruleInterface EOF )
            // InternalClassDiagram.g:390:2: iv_ruleInterface= ruleInterface EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getInterfaceRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleInterface=ruleInterface();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleInterface; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleInterface"


    // $ANTLR start "ruleInterface"
    // InternalClassDiagram.g:396:1: ruleInterface returns [EObject current=null] : (otherlv_0= 'interface' ( (lv_name_1_0= ruleSimpleName ) ) otherlv_2= '{' ( (lv_attributes_3_0= ruleAttribute ) )* ( (lv_operations_4_0= ruleOperation ) )* otherlv_5= '}' ) ;
    public final EObject ruleInterface() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_2=null;
        Token otherlv_5=null;
        AntlrDatatypeRuleToken lv_name_1_0 = null;

        EObject lv_attributes_3_0 = null;

        EObject lv_operations_4_0 = null;



        	enterRule();

        try {
            // InternalClassDiagram.g:402:2: ( (otherlv_0= 'interface' ( (lv_name_1_0= ruleSimpleName ) ) otherlv_2= '{' ( (lv_attributes_3_0= ruleAttribute ) )* ( (lv_operations_4_0= ruleOperation ) )* otherlv_5= '}' ) )
            // InternalClassDiagram.g:403:2: (otherlv_0= 'interface' ( (lv_name_1_0= ruleSimpleName ) ) otherlv_2= '{' ( (lv_attributes_3_0= ruleAttribute ) )* ( (lv_operations_4_0= ruleOperation ) )* otherlv_5= '}' )
            {
            // InternalClassDiagram.g:403:2: (otherlv_0= 'interface' ( (lv_name_1_0= ruleSimpleName ) ) otherlv_2= '{' ( (lv_attributes_3_0= ruleAttribute ) )* ( (lv_operations_4_0= ruleOperation ) )* otherlv_5= '}' )
            // InternalClassDiagram.g:404:3: otherlv_0= 'interface' ( (lv_name_1_0= ruleSimpleName ) ) otherlv_2= '{' ( (lv_attributes_3_0= ruleAttribute ) )* ( (lv_operations_4_0= ruleOperation ) )* otherlv_5= '}'
            {
            otherlv_0=(Token)match(input,20,FOLLOW_4); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_0, grammarAccess.getInterfaceAccess().getInterfaceKeyword_0());
              		
            }
            // InternalClassDiagram.g:408:3: ( (lv_name_1_0= ruleSimpleName ) )
            // InternalClassDiagram.g:409:4: (lv_name_1_0= ruleSimpleName )
            {
            // InternalClassDiagram.g:409:4: (lv_name_1_0= ruleSimpleName )
            // InternalClassDiagram.g:410:5: lv_name_1_0= ruleSimpleName
            {
            if ( state.backtracking==0 ) {

              					newCompositeNode(grammarAccess.getInterfaceAccess().getNameSimpleNameParserRuleCall_1_0());
              				
            }
            pushFollow(FOLLOW_5);
            lv_name_1_0=ruleSimpleName();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              					if (current==null) {
              						current = createModelElementForParent(grammarAccess.getInterfaceRule());
              					}
              					set(
              						current,
              						"name",
              						lv_name_1_0,
              						"com.rm2pt.rapidood.cd.ClassDiagram.SimpleName");
              					afterParserOrEnumRuleCall();
              				
            }

            }


            }

            otherlv_2=(Token)match(input,16,FOLLOW_8); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_2, grammarAccess.getInterfaceAccess().getLeftCurlyBracketKeyword_2());
              		
            }
            // InternalClassDiagram.g:431:3: ( (lv_attributes_3_0= ruleAttribute ) )*
            loop7:
            do {
                int alt7=2;
                switch ( input.LA(1) ) {
                case 28:
                    {
                    int LA7_1 = input.LA(2);

                    if ( (LA7_1==22) ) {
                        int LA7_6 = input.LA(3);

                        if ( (LA7_6==RULE_ID) ) {
                            int LA7_7 = input.LA(4);

                            if ( (LA7_7==23) ) {
                                alt7=1;
                            }


                        }


                    }
                    else if ( (LA7_1==RULE_ID) ) {
                        int LA7_7 = input.LA(3);

                        if ( (LA7_7==23) ) {
                            alt7=1;
                        }


                    }


                    }
                    break;
                case 29:
                    {
                    int LA7_2 = input.LA(2);

                    if ( (LA7_2==22) ) {
                        int LA7_6 = input.LA(3);

                        if ( (LA7_6==RULE_ID) ) {
                            int LA7_7 = input.LA(4);

                            if ( (LA7_7==23) ) {
                                alt7=1;
                            }


                        }


                    }
                    else if ( (LA7_2==RULE_ID) ) {
                        int LA7_7 = input.LA(3);

                        if ( (LA7_7==23) ) {
                            alt7=1;
                        }


                    }


                    }
                    break;
                case 30:
                    {
                    int LA7_3 = input.LA(2);

                    if ( (LA7_3==22) ) {
                        int LA7_6 = input.LA(3);

                        if ( (LA7_6==RULE_ID) ) {
                            int LA7_7 = input.LA(4);

                            if ( (LA7_7==23) ) {
                                alt7=1;
                            }


                        }


                    }
                    else if ( (LA7_3==RULE_ID) ) {
                        int LA7_7 = input.LA(3);

                        if ( (LA7_7==23) ) {
                            alt7=1;
                        }


                    }


                    }
                    break;
                case 31:
                    {
                    int LA7_4 = input.LA(2);

                    if ( (LA7_4==22) ) {
                        int LA7_6 = input.LA(3);

                        if ( (LA7_6==RULE_ID) ) {
                            int LA7_7 = input.LA(4);

                            if ( (LA7_7==23) ) {
                                alt7=1;
                            }


                        }


                    }
                    else if ( (LA7_4==RULE_ID) ) {
                        int LA7_7 = input.LA(3);

                        if ( (LA7_7==23) ) {
                            alt7=1;
                        }


                    }


                    }
                    break;
                case 22:
                    {
                    int LA7_6 = input.LA(2);

                    if ( (LA7_6==RULE_ID) ) {
                        int LA7_7 = input.LA(3);

                        if ( (LA7_7==23) ) {
                            alt7=1;
                        }


                    }


                    }
                    break;
                case RULE_ID:
                    {
                    int LA7_7 = input.LA(2);

                    if ( (LA7_7==23) ) {
                        alt7=1;
                    }


                    }
                    break;

                }

                switch (alt7) {
            	case 1 :
            	    // InternalClassDiagram.g:432:4: (lv_attributes_3_0= ruleAttribute )
            	    {
            	    // InternalClassDiagram.g:432:4: (lv_attributes_3_0= ruleAttribute )
            	    // InternalClassDiagram.g:433:5: lv_attributes_3_0= ruleAttribute
            	    {
            	    if ( state.backtracking==0 ) {

            	      					newCompositeNode(grammarAccess.getInterfaceAccess().getAttributesAttributeParserRuleCall_3_0());
            	      				
            	    }
            	    pushFollow(FOLLOW_8);
            	    lv_attributes_3_0=ruleAttribute();

            	    state._fsp--;
            	    if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      					if (current==null) {
            	      						current = createModelElementForParent(grammarAccess.getInterfaceRule());
            	      					}
            	      					add(
            	      						current,
            	      						"attributes",
            	      						lv_attributes_3_0,
            	      						"com.rm2pt.rapidood.cd.ClassDiagram.Attribute");
            	      					afterParserOrEnumRuleCall();
            	      				
            	    }

            	    }


            	    }
            	    break;

            	default :
            	    break loop7;
                }
            } while (true);

            // InternalClassDiagram.g:450:3: ( (lv_operations_4_0= ruleOperation ) )*
            loop8:
            do {
                int alt8=2;
                int LA8_0 = input.LA(1);

                if ( (LA8_0==RULE_ID||LA8_0==22||LA8_0==24||(LA8_0>=28 && LA8_0<=31)) ) {
                    alt8=1;
                }


                switch (alt8) {
            	case 1 :
            	    // InternalClassDiagram.g:451:4: (lv_operations_4_0= ruleOperation )
            	    {
            	    // InternalClassDiagram.g:451:4: (lv_operations_4_0= ruleOperation )
            	    // InternalClassDiagram.g:452:5: lv_operations_4_0= ruleOperation
            	    {
            	    if ( state.backtracking==0 ) {

            	      					newCompositeNode(grammarAccess.getInterfaceAccess().getOperationsOperationParserRuleCall_4_0());
            	      				
            	    }
            	    pushFollow(FOLLOW_8);
            	    lv_operations_4_0=ruleOperation();

            	    state._fsp--;
            	    if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      					if (current==null) {
            	      						current = createModelElementForParent(grammarAccess.getInterfaceRule());
            	      					}
            	      					add(
            	      						current,
            	      						"operations",
            	      						lv_operations_4_0,
            	      						"com.rm2pt.rapidood.cd.ClassDiagram.Operation");
            	      					afterParserOrEnumRuleCall();
            	      				
            	    }

            	    }


            	    }
            	    break;

            	default :
            	    break loop8;
                }
            } while (true);

            otherlv_5=(Token)match(input,17,FOLLOW_2); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_5, grammarAccess.getInterfaceAccess().getRightCurlyBracketKeyword_5());
              		
            }

            }


            }

            if ( state.backtracking==0 ) {

              	leaveRule();

            }
        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleInterface"


    // $ANTLR start "entryRuleAbstractClass"
    // InternalClassDiagram.g:477:1: entryRuleAbstractClass returns [EObject current=null] : iv_ruleAbstractClass= ruleAbstractClass EOF ;
    public final EObject entryRuleAbstractClass() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleAbstractClass = null;


        try {
            // InternalClassDiagram.g:477:54: (iv_ruleAbstractClass= ruleAbstractClass EOF )
            // InternalClassDiagram.g:478:2: iv_ruleAbstractClass= ruleAbstractClass EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getAbstractClassRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleAbstractClass=ruleAbstractClass();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleAbstractClass; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleAbstractClass"


    // $ANTLR start "ruleAbstractClass"
    // InternalClassDiagram.g:484:1: ruleAbstractClass returns [EObject current=null] : (otherlv_0= 'abstract' (otherlv_1= 'class' )? ( (lv_name_2_0= ruleSimpleName ) ) otherlv_3= '{' ( (lv_attributes_4_0= ruleAttribute ) )* ( (lv_operations_5_0= ruleOperation ) )* otherlv_6= '}' ) ;
    public final EObject ruleAbstractClass() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Token otherlv_3=null;
        Token otherlv_6=null;
        AntlrDatatypeRuleToken lv_name_2_0 = null;

        EObject lv_attributes_4_0 = null;

        EObject lv_operations_5_0 = null;



        	enterRule();

        try {
            // InternalClassDiagram.g:490:2: ( (otherlv_0= 'abstract' (otherlv_1= 'class' )? ( (lv_name_2_0= ruleSimpleName ) ) otherlv_3= '{' ( (lv_attributes_4_0= ruleAttribute ) )* ( (lv_operations_5_0= ruleOperation ) )* otherlv_6= '}' ) )
            // InternalClassDiagram.g:491:2: (otherlv_0= 'abstract' (otherlv_1= 'class' )? ( (lv_name_2_0= ruleSimpleName ) ) otherlv_3= '{' ( (lv_attributes_4_0= ruleAttribute ) )* ( (lv_operations_5_0= ruleOperation ) )* otherlv_6= '}' )
            {
            // InternalClassDiagram.g:491:2: (otherlv_0= 'abstract' (otherlv_1= 'class' )? ( (lv_name_2_0= ruleSimpleName ) ) otherlv_3= '{' ( (lv_attributes_4_0= ruleAttribute ) )* ( (lv_operations_5_0= ruleOperation ) )* otherlv_6= '}' )
            // InternalClassDiagram.g:492:3: otherlv_0= 'abstract' (otherlv_1= 'class' )? ( (lv_name_2_0= ruleSimpleName ) ) otherlv_3= '{' ( (lv_attributes_4_0= ruleAttribute ) )* ( (lv_operations_5_0= ruleOperation ) )* otherlv_6= '}'
            {
            otherlv_0=(Token)match(input,21,FOLLOW_9); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_0, grammarAccess.getAbstractClassAccess().getAbstractKeyword_0());
              		
            }
            // InternalClassDiagram.g:496:3: (otherlv_1= 'class' )?
            int alt9=2;
            int LA9_0 = input.LA(1);

            if ( (LA9_0==19) ) {
                alt9=1;
            }
            switch (alt9) {
                case 1 :
                    // InternalClassDiagram.g:497:4: otherlv_1= 'class'
                    {
                    otherlv_1=(Token)match(input,19,FOLLOW_4); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				newLeafNode(otherlv_1, grammarAccess.getAbstractClassAccess().getClassKeyword_1());
                      			
                    }

                    }
                    break;

            }

            // InternalClassDiagram.g:502:3: ( (lv_name_2_0= ruleSimpleName ) )
            // InternalClassDiagram.g:503:4: (lv_name_2_0= ruleSimpleName )
            {
            // InternalClassDiagram.g:503:4: (lv_name_2_0= ruleSimpleName )
            // InternalClassDiagram.g:504:5: lv_name_2_0= ruleSimpleName
            {
            if ( state.backtracking==0 ) {

              					newCompositeNode(grammarAccess.getAbstractClassAccess().getNameSimpleNameParserRuleCall_2_0());
              				
            }
            pushFollow(FOLLOW_5);
            lv_name_2_0=ruleSimpleName();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              					if (current==null) {
              						current = createModelElementForParent(grammarAccess.getAbstractClassRule());
              					}
              					set(
              						current,
              						"name",
              						lv_name_2_0,
              						"com.rm2pt.rapidood.cd.ClassDiagram.SimpleName");
              					afterParserOrEnumRuleCall();
              				
            }

            }


            }

            otherlv_3=(Token)match(input,16,FOLLOW_8); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_3, grammarAccess.getAbstractClassAccess().getLeftCurlyBracketKeyword_3());
              		
            }
            // InternalClassDiagram.g:525:3: ( (lv_attributes_4_0= ruleAttribute ) )*
            loop10:
            do {
                int alt10=2;
                switch ( input.LA(1) ) {
                case 28:
                    {
                    int LA10_1 = input.LA(2);

                    if ( (LA10_1==22) ) {
                        int LA10_6 = input.LA(3);

                        if ( (LA10_6==RULE_ID) ) {
                            int LA10_7 = input.LA(4);

                            if ( (LA10_7==23) ) {
                                alt10=1;
                            }


                        }


                    }
                    else if ( (LA10_1==RULE_ID) ) {
                        int LA10_7 = input.LA(3);

                        if ( (LA10_7==23) ) {
                            alt10=1;
                        }


                    }


                    }
                    break;
                case 29:
                    {
                    int LA10_2 = input.LA(2);

                    if ( (LA10_2==22) ) {
                        int LA10_6 = input.LA(3);

                        if ( (LA10_6==RULE_ID) ) {
                            int LA10_7 = input.LA(4);

                            if ( (LA10_7==23) ) {
                                alt10=1;
                            }


                        }


                    }
                    else if ( (LA10_2==RULE_ID) ) {
                        int LA10_7 = input.LA(3);

                        if ( (LA10_7==23) ) {
                            alt10=1;
                        }


                    }


                    }
                    break;
                case 30:
                    {
                    int LA10_3 = input.LA(2);

                    if ( (LA10_3==22) ) {
                        int LA10_6 = input.LA(3);

                        if ( (LA10_6==RULE_ID) ) {
                            int LA10_7 = input.LA(4);

                            if ( (LA10_7==23) ) {
                                alt10=1;
                            }


                        }


                    }
                    else if ( (LA10_3==RULE_ID) ) {
                        int LA10_7 = input.LA(3);

                        if ( (LA10_7==23) ) {
                            alt10=1;
                        }


                    }


                    }
                    break;
                case 31:
                    {
                    int LA10_4 = input.LA(2);

                    if ( (LA10_4==22) ) {
                        int LA10_6 = input.LA(3);

                        if ( (LA10_6==RULE_ID) ) {
                            int LA10_7 = input.LA(4);

                            if ( (LA10_7==23) ) {
                                alt10=1;
                            }


                        }


                    }
                    else if ( (LA10_4==RULE_ID) ) {
                        int LA10_7 = input.LA(3);

                        if ( (LA10_7==23) ) {
                            alt10=1;
                        }


                    }


                    }
                    break;
                case 22:
                    {
                    int LA10_6 = input.LA(2);

                    if ( (LA10_6==RULE_ID) ) {
                        int LA10_7 = input.LA(3);

                        if ( (LA10_7==23) ) {
                            alt10=1;
                        }


                    }


                    }
                    break;
                case RULE_ID:
                    {
                    int LA10_7 = input.LA(2);

                    if ( (LA10_7==23) ) {
                        alt10=1;
                    }


                    }
                    break;

                }

                switch (alt10) {
            	case 1 :
            	    // InternalClassDiagram.g:526:4: (lv_attributes_4_0= ruleAttribute )
            	    {
            	    // InternalClassDiagram.g:526:4: (lv_attributes_4_0= ruleAttribute )
            	    // InternalClassDiagram.g:527:5: lv_attributes_4_0= ruleAttribute
            	    {
            	    if ( state.backtracking==0 ) {

            	      					newCompositeNode(grammarAccess.getAbstractClassAccess().getAttributesAttributeParserRuleCall_4_0());
            	      				
            	    }
            	    pushFollow(FOLLOW_8);
            	    lv_attributes_4_0=ruleAttribute();

            	    state._fsp--;
            	    if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      					if (current==null) {
            	      						current = createModelElementForParent(grammarAccess.getAbstractClassRule());
            	      					}
            	      					add(
            	      						current,
            	      						"attributes",
            	      						lv_attributes_4_0,
            	      						"com.rm2pt.rapidood.cd.ClassDiagram.Attribute");
            	      					afterParserOrEnumRuleCall();
            	      				
            	    }

            	    }


            	    }
            	    break;

            	default :
            	    break loop10;
                }
            } while (true);

            // InternalClassDiagram.g:544:3: ( (lv_operations_5_0= ruleOperation ) )*
            loop11:
            do {
                int alt11=2;
                int LA11_0 = input.LA(1);

                if ( (LA11_0==RULE_ID||LA11_0==22||LA11_0==24||(LA11_0>=28 && LA11_0<=31)) ) {
                    alt11=1;
                }


                switch (alt11) {
            	case 1 :
            	    // InternalClassDiagram.g:545:4: (lv_operations_5_0= ruleOperation )
            	    {
            	    // InternalClassDiagram.g:545:4: (lv_operations_5_0= ruleOperation )
            	    // InternalClassDiagram.g:546:5: lv_operations_5_0= ruleOperation
            	    {
            	    if ( state.backtracking==0 ) {

            	      					newCompositeNode(grammarAccess.getAbstractClassAccess().getOperationsOperationParserRuleCall_5_0());
            	      				
            	    }
            	    pushFollow(FOLLOW_8);
            	    lv_operations_5_0=ruleOperation();

            	    state._fsp--;
            	    if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      					if (current==null) {
            	      						current = createModelElementForParent(grammarAccess.getAbstractClassRule());
            	      					}
            	      					add(
            	      						current,
            	      						"operations",
            	      						lv_operations_5_0,
            	      						"com.rm2pt.rapidood.cd.ClassDiagram.Operation");
            	      					afterParserOrEnumRuleCall();
            	      				
            	    }

            	    }


            	    }
            	    break;

            	default :
            	    break loop11;
                }
            } while (true);

            otherlv_6=(Token)match(input,17,FOLLOW_2); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_6, grammarAccess.getAbstractClassAccess().getRightCurlyBracketKeyword_6());
              		
            }

            }


            }

            if ( state.backtracking==0 ) {

              	leaveRule();

            }
        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleAbstractClass"


    // $ANTLR start "entryRuleSimpleName"
    // InternalClassDiagram.g:571:1: entryRuleSimpleName returns [String current=null] : iv_ruleSimpleName= ruleSimpleName EOF ;
    public final String entryRuleSimpleName() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleSimpleName = null;



        	HiddenTokens myHiddenTokenState = ((XtextTokenStream)input).setHiddenTokens();

        try {
            // InternalClassDiagram.g:573:2: (iv_ruleSimpleName= ruleSimpleName EOF )
            // InternalClassDiagram.g:574:2: iv_ruleSimpleName= ruleSimpleName EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getSimpleNameRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleSimpleName=ruleSimpleName();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleSimpleName.getText(); 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {

            	myHiddenTokenState.restore();

        }
        return current;
    }
    // $ANTLR end "entryRuleSimpleName"


    // $ANTLR start "ruleSimpleName"
    // InternalClassDiagram.g:583:1: ruleSimpleName returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : this_ID_0= RULE_ID ;
    public final AntlrDatatypeRuleToken ruleSimpleName() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_ID_0=null;


        	enterRule();
        	HiddenTokens myHiddenTokenState = ((XtextTokenStream)input).setHiddenTokens();

        try {
            // InternalClassDiagram.g:590:2: (this_ID_0= RULE_ID )
            // InternalClassDiagram.g:591:2: this_ID_0= RULE_ID
            {
            this_ID_0=(Token)match(input,RULE_ID,FOLLOW_2); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              		current.merge(this_ID_0);
              	
            }
            if ( state.backtracking==0 ) {

              		newLeafNode(this_ID_0, grammarAccess.getSimpleNameAccess().getIDTerminalRuleCall());
              	
            }

            }

            if ( state.backtracking==0 ) {

              	leaveRule();

            }
        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {

            	myHiddenTokenState.restore();

        }
        return current;
    }
    // $ANTLR end "ruleSimpleName"


    // $ANTLR start "entryRuleAttribute"
    // InternalClassDiagram.g:604:1: entryRuleAttribute returns [EObject current=null] : iv_ruleAttribute= ruleAttribute EOF ;
    public final EObject entryRuleAttribute() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleAttribute = null;


        try {
            // InternalClassDiagram.g:604:50: (iv_ruleAttribute= ruleAttribute EOF )
            // InternalClassDiagram.g:605:2: iv_ruleAttribute= ruleAttribute EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getAttributeRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleAttribute=ruleAttribute();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleAttribute; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleAttribute"


    // $ANTLR start "ruleAttribute"
    // InternalClassDiagram.g:611:1: ruleAttribute returns [EObject current=null] : ( ( (lv_visibility_0_0= ruleVisibility ) )? ( (lv_isStatic_1_0= '{static}' ) )? ( (lv_name_2_0= ruleSimpleName ) ) otherlv_3= ':' ( (lv_type_4_0= ruleType ) ) ) ;
    public final EObject ruleAttribute() throws RecognitionException {
        EObject current = null;

        Token lv_isStatic_1_0=null;
        Token otherlv_3=null;
        AntlrDatatypeRuleToken lv_visibility_0_0 = null;

        AntlrDatatypeRuleToken lv_name_2_0 = null;

        EObject lv_type_4_0 = null;



        	enterRule();

        try {
            // InternalClassDiagram.g:617:2: ( ( ( (lv_visibility_0_0= ruleVisibility ) )? ( (lv_isStatic_1_0= '{static}' ) )? ( (lv_name_2_0= ruleSimpleName ) ) otherlv_3= ':' ( (lv_type_4_0= ruleType ) ) ) )
            // InternalClassDiagram.g:618:2: ( ( (lv_visibility_0_0= ruleVisibility ) )? ( (lv_isStatic_1_0= '{static}' ) )? ( (lv_name_2_0= ruleSimpleName ) ) otherlv_3= ':' ( (lv_type_4_0= ruleType ) ) )
            {
            // InternalClassDiagram.g:618:2: ( ( (lv_visibility_0_0= ruleVisibility ) )? ( (lv_isStatic_1_0= '{static}' ) )? ( (lv_name_2_0= ruleSimpleName ) ) otherlv_3= ':' ( (lv_type_4_0= ruleType ) ) )
            // InternalClassDiagram.g:619:3: ( (lv_visibility_0_0= ruleVisibility ) )? ( (lv_isStatic_1_0= '{static}' ) )? ( (lv_name_2_0= ruleSimpleName ) ) otherlv_3= ':' ( (lv_type_4_0= ruleType ) )
            {
            // InternalClassDiagram.g:619:3: ( (lv_visibility_0_0= ruleVisibility ) )?
            int alt12=2;
            int LA12_0 = input.LA(1);

            if ( ((LA12_0>=28 && LA12_0<=31)) ) {
                alt12=1;
            }
            switch (alt12) {
                case 1 :
                    // InternalClassDiagram.g:620:4: (lv_visibility_0_0= ruleVisibility )
                    {
                    // InternalClassDiagram.g:620:4: (lv_visibility_0_0= ruleVisibility )
                    // InternalClassDiagram.g:621:5: lv_visibility_0_0= ruleVisibility
                    {
                    if ( state.backtracking==0 ) {

                      					newCompositeNode(grammarAccess.getAttributeAccess().getVisibilityVisibilityParserRuleCall_0_0());
                      				
                    }
                    pushFollow(FOLLOW_10);
                    lv_visibility_0_0=ruleVisibility();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      					if (current==null) {
                      						current = createModelElementForParent(grammarAccess.getAttributeRule());
                      					}
                      					set(
                      						current,
                      						"visibility",
                      						lv_visibility_0_0,
                      						"com.rm2pt.rapidood.cd.ClassDiagram.Visibility");
                      					afterParserOrEnumRuleCall();
                      				
                    }

                    }


                    }
                    break;

            }

            // InternalClassDiagram.g:638:3: ( (lv_isStatic_1_0= '{static}' ) )?
            int alt13=2;
            int LA13_0 = input.LA(1);

            if ( (LA13_0==22) ) {
                alt13=1;
            }
            switch (alt13) {
                case 1 :
                    // InternalClassDiagram.g:639:4: (lv_isStatic_1_0= '{static}' )
                    {
                    // InternalClassDiagram.g:639:4: (lv_isStatic_1_0= '{static}' )
                    // InternalClassDiagram.g:640:5: lv_isStatic_1_0= '{static}'
                    {
                    lv_isStatic_1_0=(Token)match(input,22,FOLLOW_4); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      					newLeafNode(lv_isStatic_1_0, grammarAccess.getAttributeAccess().getIsStaticStaticKeyword_1_0());
                      				
                    }
                    if ( state.backtracking==0 ) {

                      					if (current==null) {
                      						current = createModelElement(grammarAccess.getAttributeRule());
                      					}
                      					setWithLastConsumed(current, "isStatic", lv_isStatic_1_0 != null, "{static}");
                      				
                    }

                    }


                    }
                    break;

            }

            // InternalClassDiagram.g:652:3: ( (lv_name_2_0= ruleSimpleName ) )
            // InternalClassDiagram.g:653:4: (lv_name_2_0= ruleSimpleName )
            {
            // InternalClassDiagram.g:653:4: (lv_name_2_0= ruleSimpleName )
            // InternalClassDiagram.g:654:5: lv_name_2_0= ruleSimpleName
            {
            if ( state.backtracking==0 ) {

              					newCompositeNode(grammarAccess.getAttributeAccess().getNameSimpleNameParserRuleCall_2_0());
              				
            }
            pushFollow(FOLLOW_11);
            lv_name_2_0=ruleSimpleName();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              					if (current==null) {
              						current = createModelElementForParent(grammarAccess.getAttributeRule());
              					}
              					set(
              						current,
              						"name",
              						lv_name_2_0,
              						"com.rm2pt.rapidood.cd.ClassDiagram.SimpleName");
              					afterParserOrEnumRuleCall();
              				
            }

            }


            }

            otherlv_3=(Token)match(input,23,FOLLOW_12); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_3, grammarAccess.getAttributeAccess().getColonKeyword_3());
              		
            }
            // InternalClassDiagram.g:675:3: ( (lv_type_4_0= ruleType ) )
            // InternalClassDiagram.g:676:4: (lv_type_4_0= ruleType )
            {
            // InternalClassDiagram.g:676:4: (lv_type_4_0= ruleType )
            // InternalClassDiagram.g:677:5: lv_type_4_0= ruleType
            {
            if ( state.backtracking==0 ) {

              					newCompositeNode(grammarAccess.getAttributeAccess().getTypeTypeParserRuleCall_4_0());
              				
            }
            pushFollow(FOLLOW_2);
            lv_type_4_0=ruleType();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              					if (current==null) {
              						current = createModelElementForParent(grammarAccess.getAttributeRule());
              					}
              					set(
              						current,
              						"type",
              						lv_type_4_0,
              						"com.rm2pt.rapidood.cd.ClassDiagram.Type");
              					afterParserOrEnumRuleCall();
              				
            }

            }


            }


            }


            }

            if ( state.backtracking==0 ) {

              	leaveRule();

            }
        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleAttribute"


    // $ANTLR start "entryRuleOperation"
    // InternalClassDiagram.g:698:1: entryRuleOperation returns [EObject current=null] : iv_ruleOperation= ruleOperation EOF ;
    public final EObject entryRuleOperation() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleOperation = null;


        try {
            // InternalClassDiagram.g:698:50: (iv_ruleOperation= ruleOperation EOF )
            // InternalClassDiagram.g:699:2: iv_ruleOperation= ruleOperation EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getOperationRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleOperation=ruleOperation();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleOperation; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleOperation"


    // $ANTLR start "ruleOperation"
    // InternalClassDiagram.g:705:1: ruleOperation returns [EObject current=null] : ( ( (lv_visibility_0_0= ruleVisibility ) )? ( (lv_isAbstract_1_0= '{abstract}' ) )? ( (lv_isStatic_2_0= '{static}' ) )? ( (lv_name_3_0= ruleSimpleName ) ) otherlv_4= '(' ( ( (lv_params_5_0= ruleParameter ) ) (otherlv_6= ',' ( (lv_params_7_0= ruleParameter ) ) )* )? otherlv_8= ')' otherlv_9= ':' ( (lv_returnType_10_0= ruleType ) ) ) ;
    public final EObject ruleOperation() throws RecognitionException {
        EObject current = null;

        Token lv_isAbstract_1_0=null;
        Token lv_isStatic_2_0=null;
        Token otherlv_4=null;
        Token otherlv_6=null;
        Token otherlv_8=null;
        Token otherlv_9=null;
        AntlrDatatypeRuleToken lv_visibility_0_0 = null;

        AntlrDatatypeRuleToken lv_name_3_0 = null;

        EObject lv_params_5_0 = null;

        EObject lv_params_7_0 = null;

        EObject lv_returnType_10_0 = null;



        	enterRule();

        try {
            // InternalClassDiagram.g:711:2: ( ( ( (lv_visibility_0_0= ruleVisibility ) )? ( (lv_isAbstract_1_0= '{abstract}' ) )? ( (lv_isStatic_2_0= '{static}' ) )? ( (lv_name_3_0= ruleSimpleName ) ) otherlv_4= '(' ( ( (lv_params_5_0= ruleParameter ) ) (otherlv_6= ',' ( (lv_params_7_0= ruleParameter ) ) )* )? otherlv_8= ')' otherlv_9= ':' ( (lv_returnType_10_0= ruleType ) ) ) )
            // InternalClassDiagram.g:712:2: ( ( (lv_visibility_0_0= ruleVisibility ) )? ( (lv_isAbstract_1_0= '{abstract}' ) )? ( (lv_isStatic_2_0= '{static}' ) )? ( (lv_name_3_0= ruleSimpleName ) ) otherlv_4= '(' ( ( (lv_params_5_0= ruleParameter ) ) (otherlv_6= ',' ( (lv_params_7_0= ruleParameter ) ) )* )? otherlv_8= ')' otherlv_9= ':' ( (lv_returnType_10_0= ruleType ) ) )
            {
            // InternalClassDiagram.g:712:2: ( ( (lv_visibility_0_0= ruleVisibility ) )? ( (lv_isAbstract_1_0= '{abstract}' ) )? ( (lv_isStatic_2_0= '{static}' ) )? ( (lv_name_3_0= ruleSimpleName ) ) otherlv_4= '(' ( ( (lv_params_5_0= ruleParameter ) ) (otherlv_6= ',' ( (lv_params_7_0= ruleParameter ) ) )* )? otherlv_8= ')' otherlv_9= ':' ( (lv_returnType_10_0= ruleType ) ) )
            // InternalClassDiagram.g:713:3: ( (lv_visibility_0_0= ruleVisibility ) )? ( (lv_isAbstract_1_0= '{abstract}' ) )? ( (lv_isStatic_2_0= '{static}' ) )? ( (lv_name_3_0= ruleSimpleName ) ) otherlv_4= '(' ( ( (lv_params_5_0= ruleParameter ) ) (otherlv_6= ',' ( (lv_params_7_0= ruleParameter ) ) )* )? otherlv_8= ')' otherlv_9= ':' ( (lv_returnType_10_0= ruleType ) )
            {
            // InternalClassDiagram.g:713:3: ( (lv_visibility_0_0= ruleVisibility ) )?
            int alt14=2;
            int LA14_0 = input.LA(1);

            if ( ((LA14_0>=28 && LA14_0<=31)) ) {
                alt14=1;
            }
            switch (alt14) {
                case 1 :
                    // InternalClassDiagram.g:714:4: (lv_visibility_0_0= ruleVisibility )
                    {
                    // InternalClassDiagram.g:714:4: (lv_visibility_0_0= ruleVisibility )
                    // InternalClassDiagram.g:715:5: lv_visibility_0_0= ruleVisibility
                    {
                    if ( state.backtracking==0 ) {

                      					newCompositeNode(grammarAccess.getOperationAccess().getVisibilityVisibilityParserRuleCall_0_0());
                      				
                    }
                    pushFollow(FOLLOW_13);
                    lv_visibility_0_0=ruleVisibility();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      					if (current==null) {
                      						current = createModelElementForParent(grammarAccess.getOperationRule());
                      					}
                      					set(
                      						current,
                      						"visibility",
                      						lv_visibility_0_0,
                      						"com.rm2pt.rapidood.cd.ClassDiagram.Visibility");
                      					afterParserOrEnumRuleCall();
                      				
                    }

                    }


                    }
                    break;

            }

            // InternalClassDiagram.g:732:3: ( (lv_isAbstract_1_0= '{abstract}' ) )?
            int alt15=2;
            int LA15_0 = input.LA(1);

            if ( (LA15_0==24) ) {
                alt15=1;
            }
            switch (alt15) {
                case 1 :
                    // InternalClassDiagram.g:733:4: (lv_isAbstract_1_0= '{abstract}' )
                    {
                    // InternalClassDiagram.g:733:4: (lv_isAbstract_1_0= '{abstract}' )
                    // InternalClassDiagram.g:734:5: lv_isAbstract_1_0= '{abstract}'
                    {
                    lv_isAbstract_1_0=(Token)match(input,24,FOLLOW_10); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      					newLeafNode(lv_isAbstract_1_0, grammarAccess.getOperationAccess().getIsAbstractAbstractKeyword_1_0());
                      				
                    }
                    if ( state.backtracking==0 ) {

                      					if (current==null) {
                      						current = createModelElement(grammarAccess.getOperationRule());
                      					}
                      					setWithLastConsumed(current, "isAbstract", lv_isAbstract_1_0 != null, "{abstract}");
                      				
                    }

                    }


                    }
                    break;

            }

            // InternalClassDiagram.g:746:3: ( (lv_isStatic_2_0= '{static}' ) )?
            int alt16=2;
            int LA16_0 = input.LA(1);

            if ( (LA16_0==22) ) {
                alt16=1;
            }
            switch (alt16) {
                case 1 :
                    // InternalClassDiagram.g:747:4: (lv_isStatic_2_0= '{static}' )
                    {
                    // InternalClassDiagram.g:747:4: (lv_isStatic_2_0= '{static}' )
                    // InternalClassDiagram.g:748:5: lv_isStatic_2_0= '{static}'
                    {
                    lv_isStatic_2_0=(Token)match(input,22,FOLLOW_4); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      					newLeafNode(lv_isStatic_2_0, grammarAccess.getOperationAccess().getIsStaticStaticKeyword_2_0());
                      				
                    }
                    if ( state.backtracking==0 ) {

                      					if (current==null) {
                      						current = createModelElement(grammarAccess.getOperationRule());
                      					}
                      					setWithLastConsumed(current, "isStatic", lv_isStatic_2_0 != null, "{static}");
                      				
                    }

                    }


                    }
                    break;

            }

            // InternalClassDiagram.g:760:3: ( (lv_name_3_0= ruleSimpleName ) )
            // InternalClassDiagram.g:761:4: (lv_name_3_0= ruleSimpleName )
            {
            // InternalClassDiagram.g:761:4: (lv_name_3_0= ruleSimpleName )
            // InternalClassDiagram.g:762:5: lv_name_3_0= ruleSimpleName
            {
            if ( state.backtracking==0 ) {

              					newCompositeNode(grammarAccess.getOperationAccess().getNameSimpleNameParserRuleCall_3_0());
              				
            }
            pushFollow(FOLLOW_14);
            lv_name_3_0=ruleSimpleName();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              					if (current==null) {
              						current = createModelElementForParent(grammarAccess.getOperationRule());
              					}
              					set(
              						current,
              						"name",
              						lv_name_3_0,
              						"com.rm2pt.rapidood.cd.ClassDiagram.SimpleName");
              					afterParserOrEnumRuleCall();
              				
            }

            }


            }

            otherlv_4=(Token)match(input,25,FOLLOW_15); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_4, grammarAccess.getOperationAccess().getLeftParenthesisKeyword_4());
              		
            }
            // InternalClassDiagram.g:783:3: ( ( (lv_params_5_0= ruleParameter ) ) (otherlv_6= ',' ( (lv_params_7_0= ruleParameter ) ) )* )?
            int alt18=2;
            int LA18_0 = input.LA(1);

            if ( (LA18_0==RULE_ID||(LA18_0>=40 && LA18_0<=50)) ) {
                alt18=1;
            }
            switch (alt18) {
                case 1 :
                    // InternalClassDiagram.g:784:4: ( (lv_params_5_0= ruleParameter ) ) (otherlv_6= ',' ( (lv_params_7_0= ruleParameter ) ) )*
                    {
                    // InternalClassDiagram.g:784:4: ( (lv_params_5_0= ruleParameter ) )
                    // InternalClassDiagram.g:785:5: (lv_params_5_0= ruleParameter )
                    {
                    // InternalClassDiagram.g:785:5: (lv_params_5_0= ruleParameter )
                    // InternalClassDiagram.g:786:6: lv_params_5_0= ruleParameter
                    {
                    if ( state.backtracking==0 ) {

                      						newCompositeNode(grammarAccess.getOperationAccess().getParamsParameterParserRuleCall_5_0_0());
                      					
                    }
                    pushFollow(FOLLOW_16);
                    lv_params_5_0=ruleParameter();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      						if (current==null) {
                      							current = createModelElementForParent(grammarAccess.getOperationRule());
                      						}
                      						add(
                      							current,
                      							"params",
                      							lv_params_5_0,
                      							"com.rm2pt.rapidood.cd.ClassDiagram.Parameter");
                      						afterParserOrEnumRuleCall();
                      					
                    }

                    }


                    }

                    // InternalClassDiagram.g:803:4: (otherlv_6= ',' ( (lv_params_7_0= ruleParameter ) ) )*
                    loop17:
                    do {
                        int alt17=2;
                        int LA17_0 = input.LA(1);

                        if ( (LA17_0==26) ) {
                            alt17=1;
                        }


                        switch (alt17) {
                    	case 1 :
                    	    // InternalClassDiagram.g:804:5: otherlv_6= ',' ( (lv_params_7_0= ruleParameter ) )
                    	    {
                    	    otherlv_6=(Token)match(input,26,FOLLOW_12); if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	      					newLeafNode(otherlv_6, grammarAccess.getOperationAccess().getCommaKeyword_5_1_0());
                    	      				
                    	    }
                    	    // InternalClassDiagram.g:808:5: ( (lv_params_7_0= ruleParameter ) )
                    	    // InternalClassDiagram.g:809:6: (lv_params_7_0= ruleParameter )
                    	    {
                    	    // InternalClassDiagram.g:809:6: (lv_params_7_0= ruleParameter )
                    	    // InternalClassDiagram.g:810:7: lv_params_7_0= ruleParameter
                    	    {
                    	    if ( state.backtracking==0 ) {

                    	      							newCompositeNode(grammarAccess.getOperationAccess().getParamsParameterParserRuleCall_5_1_1_0());
                    	      						
                    	    }
                    	    pushFollow(FOLLOW_16);
                    	    lv_params_7_0=ruleParameter();

                    	    state._fsp--;
                    	    if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	      							if (current==null) {
                    	      								current = createModelElementForParent(grammarAccess.getOperationRule());
                    	      							}
                    	      							add(
                    	      								current,
                    	      								"params",
                    	      								lv_params_7_0,
                    	      								"com.rm2pt.rapidood.cd.ClassDiagram.Parameter");
                    	      							afterParserOrEnumRuleCall();
                    	      						
                    	    }

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop17;
                        }
                    } while (true);


                    }
                    break;

            }

            otherlv_8=(Token)match(input,27,FOLLOW_11); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_8, grammarAccess.getOperationAccess().getRightParenthesisKeyword_6());
              		
            }
            otherlv_9=(Token)match(input,23,FOLLOW_12); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_9, grammarAccess.getOperationAccess().getColonKeyword_7());
              		
            }
            // InternalClassDiagram.g:837:3: ( (lv_returnType_10_0= ruleType ) )
            // InternalClassDiagram.g:838:4: (lv_returnType_10_0= ruleType )
            {
            // InternalClassDiagram.g:838:4: (lv_returnType_10_0= ruleType )
            // InternalClassDiagram.g:839:5: lv_returnType_10_0= ruleType
            {
            if ( state.backtracking==0 ) {

              					newCompositeNode(grammarAccess.getOperationAccess().getReturnTypeTypeParserRuleCall_8_0());
              				
            }
            pushFollow(FOLLOW_2);
            lv_returnType_10_0=ruleType();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              					if (current==null) {
              						current = createModelElementForParent(grammarAccess.getOperationRule());
              					}
              					set(
              						current,
              						"returnType",
              						lv_returnType_10_0,
              						"com.rm2pt.rapidood.cd.ClassDiagram.Type");
              					afterParserOrEnumRuleCall();
              				
            }

            }


            }


            }


            }

            if ( state.backtracking==0 ) {

              	leaveRule();

            }
        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleOperation"


    // $ANTLR start "entryRuleParameter"
    // InternalClassDiagram.g:860:1: entryRuleParameter returns [EObject current=null] : iv_ruleParameter= ruleParameter EOF ;
    public final EObject entryRuleParameter() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleParameter = null;


        try {
            // InternalClassDiagram.g:860:50: (iv_ruleParameter= ruleParameter EOF )
            // InternalClassDiagram.g:861:2: iv_ruleParameter= ruleParameter EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getParameterRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleParameter=ruleParameter();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleParameter; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleParameter"


    // $ANTLR start "ruleParameter"
    // InternalClassDiagram.g:867:1: ruleParameter returns [EObject current=null] : ( ( (lv_type_0_0= ruleType ) ) ( (lv_name_1_0= ruleSimpleName ) ) ) ;
    public final EObject ruleParameter() throws RecognitionException {
        EObject current = null;

        EObject lv_type_0_0 = null;

        AntlrDatatypeRuleToken lv_name_1_0 = null;



        	enterRule();

        try {
            // InternalClassDiagram.g:873:2: ( ( ( (lv_type_0_0= ruleType ) ) ( (lv_name_1_0= ruleSimpleName ) ) ) )
            // InternalClassDiagram.g:874:2: ( ( (lv_type_0_0= ruleType ) ) ( (lv_name_1_0= ruleSimpleName ) ) )
            {
            // InternalClassDiagram.g:874:2: ( ( (lv_type_0_0= ruleType ) ) ( (lv_name_1_0= ruleSimpleName ) ) )
            // InternalClassDiagram.g:875:3: ( (lv_type_0_0= ruleType ) ) ( (lv_name_1_0= ruleSimpleName ) )
            {
            // InternalClassDiagram.g:875:3: ( (lv_type_0_0= ruleType ) )
            // InternalClassDiagram.g:876:4: (lv_type_0_0= ruleType )
            {
            // InternalClassDiagram.g:876:4: (lv_type_0_0= ruleType )
            // InternalClassDiagram.g:877:5: lv_type_0_0= ruleType
            {
            if ( state.backtracking==0 ) {

              					newCompositeNode(grammarAccess.getParameterAccess().getTypeTypeParserRuleCall_0_0());
              				
            }
            pushFollow(FOLLOW_4);
            lv_type_0_0=ruleType();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              					if (current==null) {
              						current = createModelElementForParent(grammarAccess.getParameterRule());
              					}
              					set(
              						current,
              						"type",
              						lv_type_0_0,
              						"com.rm2pt.rapidood.cd.ClassDiagram.Type");
              					afterParserOrEnumRuleCall();
              				
            }

            }


            }

            // InternalClassDiagram.g:894:3: ( (lv_name_1_0= ruleSimpleName ) )
            // InternalClassDiagram.g:895:4: (lv_name_1_0= ruleSimpleName )
            {
            // InternalClassDiagram.g:895:4: (lv_name_1_0= ruleSimpleName )
            // InternalClassDiagram.g:896:5: lv_name_1_0= ruleSimpleName
            {
            if ( state.backtracking==0 ) {

              					newCompositeNode(grammarAccess.getParameterAccess().getNameSimpleNameParserRuleCall_1_0());
              				
            }
            pushFollow(FOLLOW_2);
            lv_name_1_0=ruleSimpleName();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              					if (current==null) {
              						current = createModelElementForParent(grammarAccess.getParameterRule());
              					}
              					set(
              						current,
              						"name",
              						lv_name_1_0,
              						"com.rm2pt.rapidood.cd.ClassDiagram.SimpleName");
              					afterParserOrEnumRuleCall();
              				
            }

            }


            }


            }


            }

            if ( state.backtracking==0 ) {

              	leaveRule();

            }
        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleParameter"


    // $ANTLR start "entryRuleVisibility"
    // InternalClassDiagram.g:917:1: entryRuleVisibility returns [String current=null] : iv_ruleVisibility= ruleVisibility EOF ;
    public final String entryRuleVisibility() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleVisibility = null;


        try {
            // InternalClassDiagram.g:917:50: (iv_ruleVisibility= ruleVisibility EOF )
            // InternalClassDiagram.g:918:2: iv_ruleVisibility= ruleVisibility EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getVisibilityRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleVisibility=ruleVisibility();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleVisibility.getText(); 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleVisibility"


    // $ANTLR start "ruleVisibility"
    // InternalClassDiagram.g:924:1: ruleVisibility returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (kw= '+' | kw= '-' | kw= '#' | kw= '~' ) ;
    public final AntlrDatatypeRuleToken ruleVisibility() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;


        	enterRule();

        try {
            // InternalClassDiagram.g:930:2: ( (kw= '+' | kw= '-' | kw= '#' | kw= '~' ) )
            // InternalClassDiagram.g:931:2: (kw= '+' | kw= '-' | kw= '#' | kw= '~' )
            {
            // InternalClassDiagram.g:931:2: (kw= '+' | kw= '-' | kw= '#' | kw= '~' )
            int alt19=4;
            switch ( input.LA(1) ) {
            case 28:
                {
                alt19=1;
                }
                break;
            case 29:
                {
                alt19=2;
                }
                break;
            case 30:
                {
                alt19=3;
                }
                break;
            case 31:
                {
                alt19=4;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 19, 0, input);

                throw nvae;
            }

            switch (alt19) {
                case 1 :
                    // InternalClassDiagram.g:932:3: kw= '+'
                    {
                    kw=(Token)match(input,28,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current.merge(kw);
                      			newLeafNode(kw, grammarAccess.getVisibilityAccess().getPlusSignKeyword_0());
                      		
                    }

                    }
                    break;
                case 2 :
                    // InternalClassDiagram.g:938:3: kw= '-'
                    {
                    kw=(Token)match(input,29,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current.merge(kw);
                      			newLeafNode(kw, grammarAccess.getVisibilityAccess().getHyphenMinusKeyword_1());
                      		
                    }

                    }
                    break;
                case 3 :
                    // InternalClassDiagram.g:944:3: kw= '#'
                    {
                    kw=(Token)match(input,30,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current.merge(kw);
                      			newLeafNode(kw, grammarAccess.getVisibilityAccess().getNumberSignKeyword_2());
                      		
                    }

                    }
                    break;
                case 4 :
                    // InternalClassDiagram.g:950:3: kw= '~'
                    {
                    kw=(Token)match(input,31,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current.merge(kw);
                      			newLeafNode(kw, grammarAccess.getVisibilityAccess().getTildeKeyword_3());
                      		
                    }

                    }
                    break;

            }


            }

            if ( state.backtracking==0 ) {

              	leaveRule();

            }
        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleVisibility"


    // $ANTLR start "entryRuleRelationship"
    // InternalClassDiagram.g:959:1: entryRuleRelationship returns [EObject current=null] : iv_ruleRelationship= ruleRelationship EOF ;
    public final EObject entryRuleRelationship() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleRelationship = null;


        try {
            // InternalClassDiagram.g:959:53: (iv_ruleRelationship= ruleRelationship EOF )
            // InternalClassDiagram.g:960:2: iv_ruleRelationship= ruleRelationship EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getRelationshipRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleRelationship=ruleRelationship();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleRelationship; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleRelationship"


    // $ANTLR start "ruleRelationship"
    // InternalClassDiagram.g:966:1: ruleRelationship returns [EObject current=null] : ( () ( ( ruleFQN ) ) ( (lv_leftCardinality_2_0= ruleMultiplicity ) )? ( (lv_leftDecorator_3_0= ruleLineDecorator ) )? ( (lv_line_4_0= ruleLine ) ) ( (lv_rightDecorator_5_0= ruleLineDecorator ) )? ( (lv_rightCardinality_6_0= ruleMultiplicity ) )? ( ( ruleFQN ) ) (otherlv_8= ':' ( (lv_label_9_0= ruleLabel ) ) )? ) ;
    public final EObject ruleRelationship() throws RecognitionException {
        EObject current = null;

        Token otherlv_8=null;
        AntlrDatatypeRuleToken lv_leftCardinality_2_0 = null;

        AntlrDatatypeRuleToken lv_leftDecorator_3_0 = null;

        AntlrDatatypeRuleToken lv_line_4_0 = null;

        AntlrDatatypeRuleToken lv_rightDecorator_5_0 = null;

        AntlrDatatypeRuleToken lv_rightCardinality_6_0 = null;

        AntlrDatatypeRuleToken lv_label_9_0 = null;



        	enterRule();

        try {
            // InternalClassDiagram.g:972:2: ( ( () ( ( ruleFQN ) ) ( (lv_leftCardinality_2_0= ruleMultiplicity ) )? ( (lv_leftDecorator_3_0= ruleLineDecorator ) )? ( (lv_line_4_0= ruleLine ) ) ( (lv_rightDecorator_5_0= ruleLineDecorator ) )? ( (lv_rightCardinality_6_0= ruleMultiplicity ) )? ( ( ruleFQN ) ) (otherlv_8= ':' ( (lv_label_9_0= ruleLabel ) ) )? ) )
            // InternalClassDiagram.g:973:2: ( () ( ( ruleFQN ) ) ( (lv_leftCardinality_2_0= ruleMultiplicity ) )? ( (lv_leftDecorator_3_0= ruleLineDecorator ) )? ( (lv_line_4_0= ruleLine ) ) ( (lv_rightDecorator_5_0= ruleLineDecorator ) )? ( (lv_rightCardinality_6_0= ruleMultiplicity ) )? ( ( ruleFQN ) ) (otherlv_8= ':' ( (lv_label_9_0= ruleLabel ) ) )? )
            {
            // InternalClassDiagram.g:973:2: ( () ( ( ruleFQN ) ) ( (lv_leftCardinality_2_0= ruleMultiplicity ) )? ( (lv_leftDecorator_3_0= ruleLineDecorator ) )? ( (lv_line_4_0= ruleLine ) ) ( (lv_rightDecorator_5_0= ruleLineDecorator ) )? ( (lv_rightCardinality_6_0= ruleMultiplicity ) )? ( ( ruleFQN ) ) (otherlv_8= ':' ( (lv_label_9_0= ruleLabel ) ) )? )
            // InternalClassDiagram.g:974:3: () ( ( ruleFQN ) ) ( (lv_leftCardinality_2_0= ruleMultiplicity ) )? ( (lv_leftDecorator_3_0= ruleLineDecorator ) )? ( (lv_line_4_0= ruleLine ) ) ( (lv_rightDecorator_5_0= ruleLineDecorator ) )? ( (lv_rightCardinality_6_0= ruleMultiplicity ) )? ( ( ruleFQN ) ) (otherlv_8= ':' ( (lv_label_9_0= ruleLabel ) ) )?
            {
            // InternalClassDiagram.g:974:3: ()
            // InternalClassDiagram.g:975:4: 
            {
            if ( state.backtracking==0 ) {

              				current = forceCreateModelElement(
              					grammarAccess.getRelationshipAccess().getRelationshipAction_0(),
              					current);
              			
            }

            }

            // InternalClassDiagram.g:981:3: ( ( ruleFQN ) )
            // InternalClassDiagram.g:982:4: ( ruleFQN )
            {
            // InternalClassDiagram.g:982:4: ( ruleFQN )
            // InternalClassDiagram.g:983:5: ruleFQN
            {
            if ( state.backtracking==0 ) {

              					if (current==null) {
              						current = createModelElement(grammarAccess.getRelationshipRule());
              					}
              				
            }
            if ( state.backtracking==0 ) {

              					newCompositeNode(grammarAccess.getRelationshipAccess().getLeftClassCrossReference_1_0());
              				
            }
            pushFollow(FOLLOW_17);
            ruleFQN();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              					afterParserOrEnumRuleCall();
              				
            }

            }


            }

            // InternalClassDiagram.g:997:3: ( (lv_leftCardinality_2_0= ruleMultiplicity ) )?
            int alt20=2;
            int LA20_0 = input.LA(1);

            if ( (LA20_0==RULE_DOUBLE_QUOTED_STRING) ) {
                alt20=1;
            }
            switch (alt20) {
                case 1 :
                    // InternalClassDiagram.g:998:4: (lv_leftCardinality_2_0= ruleMultiplicity )
                    {
                    // InternalClassDiagram.g:998:4: (lv_leftCardinality_2_0= ruleMultiplicity )
                    // InternalClassDiagram.g:999:5: lv_leftCardinality_2_0= ruleMultiplicity
                    {
                    if ( state.backtracking==0 ) {

                      					newCompositeNode(grammarAccess.getRelationshipAccess().getLeftCardinalityMultiplicityParserRuleCall_2_0());
                      				
                    }
                    pushFollow(FOLLOW_17);
                    lv_leftCardinality_2_0=ruleMultiplicity();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      					if (current==null) {
                      						current = createModelElementForParent(grammarAccess.getRelationshipRule());
                      					}
                      					set(
                      						current,
                      						"leftCardinality",
                      						lv_leftCardinality_2_0,
                      						"com.rm2pt.rapidood.cd.ClassDiagram.Multiplicity");
                      					afterParserOrEnumRuleCall();
                      				
                    }

                    }


                    }
                    break;

            }

            // InternalClassDiagram.g:1016:3: ( (lv_leftDecorator_3_0= ruleLineDecorator ) )?
            int alt21=2;
            int LA21_0 = input.LA(1);

            if ( ((LA21_0>=32 && LA21_0<=37)) ) {
                alt21=1;
            }
            switch (alt21) {
                case 1 :
                    // InternalClassDiagram.g:1017:4: (lv_leftDecorator_3_0= ruleLineDecorator )
                    {
                    // InternalClassDiagram.g:1017:4: (lv_leftDecorator_3_0= ruleLineDecorator )
                    // InternalClassDiagram.g:1018:5: lv_leftDecorator_3_0= ruleLineDecorator
                    {
                    if ( state.backtracking==0 ) {

                      					newCompositeNode(grammarAccess.getRelationshipAccess().getLeftDecoratorLineDecoratorParserRuleCall_3_0());
                      				
                    }
                    pushFollow(FOLLOW_17);
                    lv_leftDecorator_3_0=ruleLineDecorator();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      					if (current==null) {
                      						current = createModelElementForParent(grammarAccess.getRelationshipRule());
                      					}
                      					set(
                      						current,
                      						"leftDecorator",
                      						lv_leftDecorator_3_0,
                      						"com.rm2pt.rapidood.cd.ClassDiagram.LineDecorator");
                      					afterParserOrEnumRuleCall();
                      				
                    }

                    }


                    }
                    break;

            }

            // InternalClassDiagram.g:1035:3: ( (lv_line_4_0= ruleLine ) )
            // InternalClassDiagram.g:1036:4: (lv_line_4_0= ruleLine )
            {
            // InternalClassDiagram.g:1036:4: (lv_line_4_0= ruleLine )
            // InternalClassDiagram.g:1037:5: lv_line_4_0= ruleLine
            {
            if ( state.backtracking==0 ) {

              					newCompositeNode(grammarAccess.getRelationshipAccess().getLineLineParserRuleCall_4_0());
              				
            }
            pushFollow(FOLLOW_18);
            lv_line_4_0=ruleLine();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              					if (current==null) {
              						current = createModelElementForParent(grammarAccess.getRelationshipRule());
              					}
              					set(
              						current,
              						"line",
              						lv_line_4_0,
              						"com.rm2pt.rapidood.cd.ClassDiagram.Line");
              					afterParserOrEnumRuleCall();
              				
            }

            }


            }

            // InternalClassDiagram.g:1054:3: ( (lv_rightDecorator_5_0= ruleLineDecorator ) )?
            int alt22=2;
            int LA22_0 = input.LA(1);

            if ( ((LA22_0>=32 && LA22_0<=37)) ) {
                alt22=1;
            }
            switch (alt22) {
                case 1 :
                    // InternalClassDiagram.g:1055:4: (lv_rightDecorator_5_0= ruleLineDecorator )
                    {
                    // InternalClassDiagram.g:1055:4: (lv_rightDecorator_5_0= ruleLineDecorator )
                    // InternalClassDiagram.g:1056:5: lv_rightDecorator_5_0= ruleLineDecorator
                    {
                    if ( state.backtracking==0 ) {

                      					newCompositeNode(grammarAccess.getRelationshipAccess().getRightDecoratorLineDecoratorParserRuleCall_5_0());
                      				
                    }
                    pushFollow(FOLLOW_19);
                    lv_rightDecorator_5_0=ruleLineDecorator();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      					if (current==null) {
                      						current = createModelElementForParent(grammarAccess.getRelationshipRule());
                      					}
                      					set(
                      						current,
                      						"rightDecorator",
                      						lv_rightDecorator_5_0,
                      						"com.rm2pt.rapidood.cd.ClassDiagram.LineDecorator");
                      					afterParserOrEnumRuleCall();
                      				
                    }

                    }


                    }
                    break;

            }

            // InternalClassDiagram.g:1073:3: ( (lv_rightCardinality_6_0= ruleMultiplicity ) )?
            int alt23=2;
            int LA23_0 = input.LA(1);

            if ( (LA23_0==RULE_DOUBLE_QUOTED_STRING) ) {
                alt23=1;
            }
            switch (alt23) {
                case 1 :
                    // InternalClassDiagram.g:1074:4: (lv_rightCardinality_6_0= ruleMultiplicity )
                    {
                    // InternalClassDiagram.g:1074:4: (lv_rightCardinality_6_0= ruleMultiplicity )
                    // InternalClassDiagram.g:1075:5: lv_rightCardinality_6_0= ruleMultiplicity
                    {
                    if ( state.backtracking==0 ) {

                      					newCompositeNode(grammarAccess.getRelationshipAccess().getRightCardinalityMultiplicityParserRuleCall_6_0());
                      				
                    }
                    pushFollow(FOLLOW_4);
                    lv_rightCardinality_6_0=ruleMultiplicity();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      					if (current==null) {
                      						current = createModelElementForParent(grammarAccess.getRelationshipRule());
                      					}
                      					set(
                      						current,
                      						"rightCardinality",
                      						lv_rightCardinality_6_0,
                      						"com.rm2pt.rapidood.cd.ClassDiagram.Multiplicity");
                      					afterParserOrEnumRuleCall();
                      				
                    }

                    }


                    }
                    break;

            }

            // InternalClassDiagram.g:1092:3: ( ( ruleFQN ) )
            // InternalClassDiagram.g:1093:4: ( ruleFQN )
            {
            // InternalClassDiagram.g:1093:4: ( ruleFQN )
            // InternalClassDiagram.g:1094:5: ruleFQN
            {
            if ( state.backtracking==0 ) {

              					if (current==null) {
              						current = createModelElement(grammarAccess.getRelationshipRule());
              					}
              				
            }
            if ( state.backtracking==0 ) {

              					newCompositeNode(grammarAccess.getRelationshipAccess().getRightClassCrossReference_7_0());
              				
            }
            pushFollow(FOLLOW_20);
            ruleFQN();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              					afterParserOrEnumRuleCall();
              				
            }

            }


            }

            // InternalClassDiagram.g:1108:3: (otherlv_8= ':' ( (lv_label_9_0= ruleLabel ) ) )?
            int alt24=2;
            int LA24_0 = input.LA(1);

            if ( (LA24_0==23) ) {
                alt24=1;
            }
            switch (alt24) {
                case 1 :
                    // InternalClassDiagram.g:1109:4: otherlv_8= ':' ( (lv_label_9_0= ruleLabel ) )
                    {
                    otherlv_8=(Token)match(input,23,FOLLOW_4); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				newLeafNode(otherlv_8, grammarAccess.getRelationshipAccess().getColonKeyword_8_0());
                      			
                    }
                    // InternalClassDiagram.g:1113:4: ( (lv_label_9_0= ruleLabel ) )
                    // InternalClassDiagram.g:1114:5: (lv_label_9_0= ruleLabel )
                    {
                    // InternalClassDiagram.g:1114:5: (lv_label_9_0= ruleLabel )
                    // InternalClassDiagram.g:1115:6: lv_label_9_0= ruleLabel
                    {
                    if ( state.backtracking==0 ) {

                      						newCompositeNode(grammarAccess.getRelationshipAccess().getLabelLabelParserRuleCall_8_1_0());
                      					
                    }
                    pushFollow(FOLLOW_2);
                    lv_label_9_0=ruleLabel();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      						if (current==null) {
                      							current = createModelElementForParent(grammarAccess.getRelationshipRule());
                      						}
                      						set(
                      							current,
                      							"label",
                      							lv_label_9_0,
                      							"com.rm2pt.rapidood.cd.ClassDiagram.Label");
                      						afterParserOrEnumRuleCall();
                      					
                    }

                    }


                    }


                    }
                    break;

            }


            }


            }

            if ( state.backtracking==0 ) {

              	leaveRule();

            }
        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleRelationship"


    // $ANTLR start "entryRuleMultiplicity"
    // InternalClassDiagram.g:1137:1: entryRuleMultiplicity returns [String current=null] : iv_ruleMultiplicity= ruleMultiplicity EOF ;
    public final String entryRuleMultiplicity() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleMultiplicity = null;


        try {
            // InternalClassDiagram.g:1137:52: (iv_ruleMultiplicity= ruleMultiplicity EOF )
            // InternalClassDiagram.g:1138:2: iv_ruleMultiplicity= ruleMultiplicity EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getMultiplicityRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleMultiplicity=ruleMultiplicity();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleMultiplicity.getText(); 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleMultiplicity"


    // $ANTLR start "ruleMultiplicity"
    // InternalClassDiagram.g:1144:1: ruleMultiplicity returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : this_DOUBLE_QUOTED_STRING_0= RULE_DOUBLE_QUOTED_STRING ;
    public final AntlrDatatypeRuleToken ruleMultiplicity() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_DOUBLE_QUOTED_STRING_0=null;


        	enterRule();

        try {
            // InternalClassDiagram.g:1150:2: (this_DOUBLE_QUOTED_STRING_0= RULE_DOUBLE_QUOTED_STRING )
            // InternalClassDiagram.g:1151:2: this_DOUBLE_QUOTED_STRING_0= RULE_DOUBLE_QUOTED_STRING
            {
            this_DOUBLE_QUOTED_STRING_0=(Token)match(input,RULE_DOUBLE_QUOTED_STRING,FOLLOW_2); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              		current.merge(this_DOUBLE_QUOTED_STRING_0);
              	
            }
            if ( state.backtracking==0 ) {

              		newLeafNode(this_DOUBLE_QUOTED_STRING_0, grammarAccess.getMultiplicityAccess().getDOUBLE_QUOTED_STRINGTerminalRuleCall());
              	
            }

            }

            if ( state.backtracking==0 ) {

              	leaveRule();

            }
        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleMultiplicity"


    // $ANTLR start "entryRuleLineDecorator"
    // InternalClassDiagram.g:1161:1: entryRuleLineDecorator returns [String current=null] : iv_ruleLineDecorator= ruleLineDecorator EOF ;
    public final String entryRuleLineDecorator() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleLineDecorator = null;


        try {
            // InternalClassDiagram.g:1161:53: (iv_ruleLineDecorator= ruleLineDecorator EOF )
            // InternalClassDiagram.g:1162:2: iv_ruleLineDecorator= ruleLineDecorator EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getLineDecoratorRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleLineDecorator=ruleLineDecorator();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleLineDecorator.getText(); 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleLineDecorator"


    // $ANTLR start "ruleLineDecorator"
    // InternalClassDiagram.g:1168:1: ruleLineDecorator returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (kw= '<|' | kw= '|>' | kw= 'o' | kw= '*' | kw= '<' | kw= '>' ) ;
    public final AntlrDatatypeRuleToken ruleLineDecorator() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;


        	enterRule();

        try {
            // InternalClassDiagram.g:1174:2: ( (kw= '<|' | kw= '|>' | kw= 'o' | kw= '*' | kw= '<' | kw= '>' ) )
            // InternalClassDiagram.g:1175:2: (kw= '<|' | kw= '|>' | kw= 'o' | kw= '*' | kw= '<' | kw= '>' )
            {
            // InternalClassDiagram.g:1175:2: (kw= '<|' | kw= '|>' | kw= 'o' | kw= '*' | kw= '<' | kw= '>' )
            int alt25=6;
            switch ( input.LA(1) ) {
            case 32:
                {
                alt25=1;
                }
                break;
            case 33:
                {
                alt25=2;
                }
                break;
            case 34:
                {
                alt25=3;
                }
                break;
            case 35:
                {
                alt25=4;
                }
                break;
            case 36:
                {
                alt25=5;
                }
                break;
            case 37:
                {
                alt25=6;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 25, 0, input);

                throw nvae;
            }

            switch (alt25) {
                case 1 :
                    // InternalClassDiagram.g:1176:3: kw= '<|'
                    {
                    kw=(Token)match(input,32,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current.merge(kw);
                      			newLeafNode(kw, grammarAccess.getLineDecoratorAccess().getLessThanSignVerticalLineKeyword_0());
                      		
                    }

                    }
                    break;
                case 2 :
                    // InternalClassDiagram.g:1182:3: kw= '|>'
                    {
                    kw=(Token)match(input,33,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current.merge(kw);
                      			newLeafNode(kw, grammarAccess.getLineDecoratorAccess().getVerticalLineGreaterThanSignKeyword_1());
                      		
                    }

                    }
                    break;
                case 3 :
                    // InternalClassDiagram.g:1188:3: kw= 'o'
                    {
                    kw=(Token)match(input,34,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current.merge(kw);
                      			newLeafNode(kw, grammarAccess.getLineDecoratorAccess().getOKeyword_2());
                      		
                    }

                    }
                    break;
                case 4 :
                    // InternalClassDiagram.g:1194:3: kw= '*'
                    {
                    kw=(Token)match(input,35,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current.merge(kw);
                      			newLeafNode(kw, grammarAccess.getLineDecoratorAccess().getAsteriskKeyword_3());
                      		
                    }

                    }
                    break;
                case 5 :
                    // InternalClassDiagram.g:1200:3: kw= '<'
                    {
                    kw=(Token)match(input,36,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current.merge(kw);
                      			newLeafNode(kw, grammarAccess.getLineDecoratorAccess().getLessThanSignKeyword_4());
                      		
                    }

                    }
                    break;
                case 6 :
                    // InternalClassDiagram.g:1206:3: kw= '>'
                    {
                    kw=(Token)match(input,37,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current.merge(kw);
                      			newLeafNode(kw, grammarAccess.getLineDecoratorAccess().getGreaterThanSignKeyword_5());
                      		
                    }

                    }
                    break;

            }


            }

            if ( state.backtracking==0 ) {

              	leaveRule();

            }
        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleLineDecorator"


    // $ANTLR start "entryRuleLine"
    // InternalClassDiagram.g:1215:1: entryRuleLine returns [String current=null] : iv_ruleLine= ruleLine EOF ;
    public final String entryRuleLine() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleLine = null;


        try {
            // InternalClassDiagram.g:1215:44: (iv_ruleLine= ruleLine EOF )
            // InternalClassDiagram.g:1216:2: iv_ruleLine= ruleLine EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getLineRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleLine=ruleLine();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleLine.getText(); 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleLine"


    // $ANTLR start "ruleLine"
    // InternalClassDiagram.g:1222:1: ruleLine returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (kw= '--' | kw= '-' | kw= '..' | kw= '.' ) ;
    public final AntlrDatatypeRuleToken ruleLine() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;


        	enterRule();

        try {
            // InternalClassDiagram.g:1228:2: ( (kw= '--' | kw= '-' | kw= '..' | kw= '.' ) )
            // InternalClassDiagram.g:1229:2: (kw= '--' | kw= '-' | kw= '..' | kw= '.' )
            {
            // InternalClassDiagram.g:1229:2: (kw= '--' | kw= '-' | kw= '..' | kw= '.' )
            int alt26=4;
            switch ( input.LA(1) ) {
            case 38:
                {
                alt26=1;
                }
                break;
            case 29:
                {
                alt26=2;
                }
                break;
            case 39:
                {
                alt26=3;
                }
                break;
            case 18:
                {
                alt26=4;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 26, 0, input);

                throw nvae;
            }

            switch (alt26) {
                case 1 :
                    // InternalClassDiagram.g:1230:3: kw= '--'
                    {
                    kw=(Token)match(input,38,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current.merge(kw);
                      			newLeafNode(kw, grammarAccess.getLineAccess().getHyphenMinusHyphenMinusKeyword_0());
                      		
                    }

                    }
                    break;
                case 2 :
                    // InternalClassDiagram.g:1236:3: kw= '-'
                    {
                    kw=(Token)match(input,29,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current.merge(kw);
                      			newLeafNode(kw, grammarAccess.getLineAccess().getHyphenMinusKeyword_1());
                      		
                    }

                    }
                    break;
                case 3 :
                    // InternalClassDiagram.g:1242:3: kw= '..'
                    {
                    kw=(Token)match(input,39,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current.merge(kw);
                      			newLeafNode(kw, grammarAccess.getLineAccess().getFullStopFullStopKeyword_2());
                      		
                    }

                    }
                    break;
                case 4 :
                    // InternalClassDiagram.g:1248:3: kw= '.'
                    {
                    kw=(Token)match(input,18,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current.merge(kw);
                      			newLeafNode(kw, grammarAccess.getLineAccess().getFullStopKeyword_3());
                      		
                    }

                    }
                    break;

            }


            }

            if ( state.backtracking==0 ) {

              	leaveRule();

            }
        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleLine"


    // $ANTLR start "entryRuleLabel"
    // InternalClassDiagram.g:1257:1: entryRuleLabel returns [String current=null] : iv_ruleLabel= ruleLabel EOF ;
    public final String entryRuleLabel() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleLabel = null;


        try {
            // InternalClassDiagram.g:1257:45: (iv_ruleLabel= ruleLabel EOF )
            // InternalClassDiagram.g:1258:2: iv_ruleLabel= ruleLabel EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getLabelRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleLabel=ruleLabel();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleLabel.getText(); 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleLabel"


    // $ANTLR start "ruleLabel"
    // InternalClassDiagram.g:1264:1: ruleLabel returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (this_ID_0= RULE_ID )+ ;
    public final AntlrDatatypeRuleToken ruleLabel() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_ID_0=null;


        	enterRule();

        try {
            // InternalClassDiagram.g:1270:2: ( (this_ID_0= RULE_ID )+ )
            // InternalClassDiagram.g:1271:2: (this_ID_0= RULE_ID )+
            {
            // InternalClassDiagram.g:1271:2: (this_ID_0= RULE_ID )+
            int cnt27=0;
            loop27:
            do {
                int alt27=2;
                int LA27_0 = input.LA(1);

                if ( (LA27_0==RULE_ID) ) {
                    int LA27_2 = input.LA(2);

                    if ( (LA27_2==EOF||LA27_2==RULE_ID||(LA27_2>=14 && LA27_2<=15)||LA27_2==17||(LA27_2>=19 && LA27_2<=21)||LA27_2==51) ) {
                        alt27=1;
                    }


                }


                switch (alt27) {
            	case 1 :
            	    // InternalClassDiagram.g:1272:3: this_ID_0= RULE_ID
            	    {
            	    this_ID_0=(Token)match(input,RULE_ID,FOLLOW_21); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      			current.merge(this_ID_0);
            	      		
            	    }
            	    if ( state.backtracking==0 ) {

            	      			newLeafNode(this_ID_0, grammarAccess.getLabelAccess().getIDTerminalRuleCall());
            	      		
            	    }

            	    }
            	    break;

            	default :
            	    if ( cnt27 >= 1 ) break loop27;
            	    if (state.backtracking>0) {state.failed=true; return current;}
                        EarlyExitException eee =
                            new EarlyExitException(27, input);
                        throw eee;
                }
                cnt27++;
            } while (true);


            }

            if ( state.backtracking==0 ) {

              	leaveRule();

            }
        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleLabel"


    // $ANTLR start "entryRuleType"
    // InternalClassDiagram.g:1283:1: entryRuleType returns [EObject current=null] : iv_ruleType= ruleType EOF ;
    public final EObject entryRuleType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleType = null;


        try {
            // InternalClassDiagram.g:1283:45: (iv_ruleType= ruleType EOF )
            // InternalClassDiagram.g:1284:2: iv_ruleType= ruleType EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getTypeRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleType=ruleType();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleType; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleType"


    // $ANTLR start "ruleType"
    // InternalClassDiagram.g:1290:1: ruleType returns [EObject current=null] : (this_PrimitiveType_0= rulePrimitiveType | this_ReferType_1= ruleReferType ) ;
    public final EObject ruleType() throws RecognitionException {
        EObject current = null;

        EObject this_PrimitiveType_0 = null;

        EObject this_ReferType_1 = null;



        	enterRule();

        try {
            // InternalClassDiagram.g:1296:2: ( (this_PrimitiveType_0= rulePrimitiveType | this_ReferType_1= ruleReferType ) )
            // InternalClassDiagram.g:1297:2: (this_PrimitiveType_0= rulePrimitiveType | this_ReferType_1= ruleReferType )
            {
            // InternalClassDiagram.g:1297:2: (this_PrimitiveType_0= rulePrimitiveType | this_ReferType_1= ruleReferType )
            int alt28=2;
            int LA28_0 = input.LA(1);

            if ( ((LA28_0>=40 && LA28_0<=50)) ) {
                alt28=1;
            }
            else if ( (LA28_0==RULE_ID) ) {
                alt28=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 28, 0, input);

                throw nvae;
            }
            switch (alt28) {
                case 1 :
                    // InternalClassDiagram.g:1298:3: this_PrimitiveType_0= rulePrimitiveType
                    {
                    if ( state.backtracking==0 ) {

                      			newCompositeNode(grammarAccess.getTypeAccess().getPrimitiveTypeParserRuleCall_0());
                      		
                    }
                    pushFollow(FOLLOW_2);
                    this_PrimitiveType_0=rulePrimitiveType();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current = this_PrimitiveType_0;
                      			afterParserOrEnumRuleCall();
                      		
                    }

                    }
                    break;
                case 2 :
                    // InternalClassDiagram.g:1307:3: this_ReferType_1= ruleReferType
                    {
                    if ( state.backtracking==0 ) {

                      			newCompositeNode(grammarAccess.getTypeAccess().getReferTypeParserRuleCall_1());
                      		
                    }
                    pushFollow(FOLLOW_2);
                    this_ReferType_1=ruleReferType();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current = this_ReferType_1;
                      			afterParserOrEnumRuleCall();
                      		
                    }

                    }
                    break;

            }


            }

            if ( state.backtracking==0 ) {

              	leaveRule();

            }
        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleType"


    // $ANTLR start "entryRulePrimitiveType"
    // InternalClassDiagram.g:1319:1: entryRulePrimitiveType returns [EObject current=null] : iv_rulePrimitiveType= rulePrimitiveType EOF ;
    public final EObject entryRulePrimitiveType() throws RecognitionException {
        EObject current = null;

        EObject iv_rulePrimitiveType = null;


        try {
            // InternalClassDiagram.g:1319:54: (iv_rulePrimitiveType= rulePrimitiveType EOF )
            // InternalClassDiagram.g:1320:2: iv_rulePrimitiveType= rulePrimitiveType EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getPrimitiveTypeRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_rulePrimitiveType=rulePrimitiveType();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_rulePrimitiveType; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRulePrimitiveType"


    // $ANTLR start "rulePrimitiveType"
    // InternalClassDiagram.g:1326:1: rulePrimitiveType returns [EObject current=null] : ( ( (lv_name_0_1= 'Boolean' | lv_name_0_2= 'Integer' | lv_name_0_3= 'Real' | lv_name_0_4= 'String' | lv_name_0_5= 'Double' | lv_name_0_6= 'UnlimitedNatural' | lv_name_0_7= 'Date' | lv_name_0_8= 'void' | lv_name_0_9= 'int' | lv_name_0_10= 'boolean' | lv_name_0_11= 'double' ) ) ) ;
    public final EObject rulePrimitiveType() throws RecognitionException {
        EObject current = null;

        Token lv_name_0_1=null;
        Token lv_name_0_2=null;
        Token lv_name_0_3=null;
        Token lv_name_0_4=null;
        Token lv_name_0_5=null;
        Token lv_name_0_6=null;
        Token lv_name_0_7=null;
        Token lv_name_0_8=null;
        Token lv_name_0_9=null;
        Token lv_name_0_10=null;
        Token lv_name_0_11=null;


        	enterRule();

        try {
            // InternalClassDiagram.g:1332:2: ( ( ( (lv_name_0_1= 'Boolean' | lv_name_0_2= 'Integer' | lv_name_0_3= 'Real' | lv_name_0_4= 'String' | lv_name_0_5= 'Double' | lv_name_0_6= 'UnlimitedNatural' | lv_name_0_7= 'Date' | lv_name_0_8= 'void' | lv_name_0_9= 'int' | lv_name_0_10= 'boolean' | lv_name_0_11= 'double' ) ) ) )
            // InternalClassDiagram.g:1333:2: ( ( (lv_name_0_1= 'Boolean' | lv_name_0_2= 'Integer' | lv_name_0_3= 'Real' | lv_name_0_4= 'String' | lv_name_0_5= 'Double' | lv_name_0_6= 'UnlimitedNatural' | lv_name_0_7= 'Date' | lv_name_0_8= 'void' | lv_name_0_9= 'int' | lv_name_0_10= 'boolean' | lv_name_0_11= 'double' ) ) )
            {
            // InternalClassDiagram.g:1333:2: ( ( (lv_name_0_1= 'Boolean' | lv_name_0_2= 'Integer' | lv_name_0_3= 'Real' | lv_name_0_4= 'String' | lv_name_0_5= 'Double' | lv_name_0_6= 'UnlimitedNatural' | lv_name_0_7= 'Date' | lv_name_0_8= 'void' | lv_name_0_9= 'int' | lv_name_0_10= 'boolean' | lv_name_0_11= 'double' ) ) )
            // InternalClassDiagram.g:1334:3: ( (lv_name_0_1= 'Boolean' | lv_name_0_2= 'Integer' | lv_name_0_3= 'Real' | lv_name_0_4= 'String' | lv_name_0_5= 'Double' | lv_name_0_6= 'UnlimitedNatural' | lv_name_0_7= 'Date' | lv_name_0_8= 'void' | lv_name_0_9= 'int' | lv_name_0_10= 'boolean' | lv_name_0_11= 'double' ) )
            {
            // InternalClassDiagram.g:1334:3: ( (lv_name_0_1= 'Boolean' | lv_name_0_2= 'Integer' | lv_name_0_3= 'Real' | lv_name_0_4= 'String' | lv_name_0_5= 'Double' | lv_name_0_6= 'UnlimitedNatural' | lv_name_0_7= 'Date' | lv_name_0_8= 'void' | lv_name_0_9= 'int' | lv_name_0_10= 'boolean' | lv_name_0_11= 'double' ) )
            // InternalClassDiagram.g:1335:4: (lv_name_0_1= 'Boolean' | lv_name_0_2= 'Integer' | lv_name_0_3= 'Real' | lv_name_0_4= 'String' | lv_name_0_5= 'Double' | lv_name_0_6= 'UnlimitedNatural' | lv_name_0_7= 'Date' | lv_name_0_8= 'void' | lv_name_0_9= 'int' | lv_name_0_10= 'boolean' | lv_name_0_11= 'double' )
            {
            // InternalClassDiagram.g:1335:4: (lv_name_0_1= 'Boolean' | lv_name_0_2= 'Integer' | lv_name_0_3= 'Real' | lv_name_0_4= 'String' | lv_name_0_5= 'Double' | lv_name_0_6= 'UnlimitedNatural' | lv_name_0_7= 'Date' | lv_name_0_8= 'void' | lv_name_0_9= 'int' | lv_name_0_10= 'boolean' | lv_name_0_11= 'double' )
            int alt29=11;
            switch ( input.LA(1) ) {
            case 40:
                {
                alt29=1;
                }
                break;
            case 41:
                {
                alt29=2;
                }
                break;
            case 42:
                {
                alt29=3;
                }
                break;
            case 43:
                {
                alt29=4;
                }
                break;
            case 44:
                {
                alt29=5;
                }
                break;
            case 45:
                {
                alt29=6;
                }
                break;
            case 46:
                {
                alt29=7;
                }
                break;
            case 47:
                {
                alt29=8;
                }
                break;
            case 48:
                {
                alt29=9;
                }
                break;
            case 49:
                {
                alt29=10;
                }
                break;
            case 50:
                {
                alt29=11;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 29, 0, input);

                throw nvae;
            }

            switch (alt29) {
                case 1 :
                    // InternalClassDiagram.g:1336:5: lv_name_0_1= 'Boolean'
                    {
                    lv_name_0_1=(Token)match(input,40,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      					newLeafNode(lv_name_0_1, grammarAccess.getPrimitiveTypeAccess().getNameBooleanKeyword_0_0());
                      				
                    }
                    if ( state.backtracking==0 ) {

                      					if (current==null) {
                      						current = createModelElement(grammarAccess.getPrimitiveTypeRule());
                      					}
                      					setWithLastConsumed(current, "name", lv_name_0_1, null);
                      				
                    }

                    }
                    break;
                case 2 :
                    // InternalClassDiagram.g:1347:5: lv_name_0_2= 'Integer'
                    {
                    lv_name_0_2=(Token)match(input,41,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      					newLeafNode(lv_name_0_2, grammarAccess.getPrimitiveTypeAccess().getNameIntegerKeyword_0_1());
                      				
                    }
                    if ( state.backtracking==0 ) {

                      					if (current==null) {
                      						current = createModelElement(grammarAccess.getPrimitiveTypeRule());
                      					}
                      					setWithLastConsumed(current, "name", lv_name_0_2, null);
                      				
                    }

                    }
                    break;
                case 3 :
                    // InternalClassDiagram.g:1358:5: lv_name_0_3= 'Real'
                    {
                    lv_name_0_3=(Token)match(input,42,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      					newLeafNode(lv_name_0_3, grammarAccess.getPrimitiveTypeAccess().getNameRealKeyword_0_2());
                      				
                    }
                    if ( state.backtracking==0 ) {

                      					if (current==null) {
                      						current = createModelElement(grammarAccess.getPrimitiveTypeRule());
                      					}
                      					setWithLastConsumed(current, "name", lv_name_0_3, null);
                      				
                    }

                    }
                    break;
                case 4 :
                    // InternalClassDiagram.g:1369:5: lv_name_0_4= 'String'
                    {
                    lv_name_0_4=(Token)match(input,43,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      					newLeafNode(lv_name_0_4, grammarAccess.getPrimitiveTypeAccess().getNameStringKeyword_0_3());
                      				
                    }
                    if ( state.backtracking==0 ) {

                      					if (current==null) {
                      						current = createModelElement(grammarAccess.getPrimitiveTypeRule());
                      					}
                      					setWithLastConsumed(current, "name", lv_name_0_4, null);
                      				
                    }

                    }
                    break;
                case 5 :
                    // InternalClassDiagram.g:1380:5: lv_name_0_5= 'Double'
                    {
                    lv_name_0_5=(Token)match(input,44,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      					newLeafNode(lv_name_0_5, grammarAccess.getPrimitiveTypeAccess().getNameDoubleKeyword_0_4());
                      				
                    }
                    if ( state.backtracking==0 ) {

                      					if (current==null) {
                      						current = createModelElement(grammarAccess.getPrimitiveTypeRule());
                      					}
                      					setWithLastConsumed(current, "name", lv_name_0_5, null);
                      				
                    }

                    }
                    break;
                case 6 :
                    // InternalClassDiagram.g:1391:5: lv_name_0_6= 'UnlimitedNatural'
                    {
                    lv_name_0_6=(Token)match(input,45,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      					newLeafNode(lv_name_0_6, grammarAccess.getPrimitiveTypeAccess().getNameUnlimitedNaturalKeyword_0_5());
                      				
                    }
                    if ( state.backtracking==0 ) {

                      					if (current==null) {
                      						current = createModelElement(grammarAccess.getPrimitiveTypeRule());
                      					}
                      					setWithLastConsumed(current, "name", lv_name_0_6, null);
                      				
                    }

                    }
                    break;
                case 7 :
                    // InternalClassDiagram.g:1402:5: lv_name_0_7= 'Date'
                    {
                    lv_name_0_7=(Token)match(input,46,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      					newLeafNode(lv_name_0_7, grammarAccess.getPrimitiveTypeAccess().getNameDateKeyword_0_6());
                      				
                    }
                    if ( state.backtracking==0 ) {

                      					if (current==null) {
                      						current = createModelElement(grammarAccess.getPrimitiveTypeRule());
                      					}
                      					setWithLastConsumed(current, "name", lv_name_0_7, null);
                      				
                    }

                    }
                    break;
                case 8 :
                    // InternalClassDiagram.g:1413:5: lv_name_0_8= 'void'
                    {
                    lv_name_0_8=(Token)match(input,47,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      					newLeafNode(lv_name_0_8, grammarAccess.getPrimitiveTypeAccess().getNameVoidKeyword_0_7());
                      				
                    }
                    if ( state.backtracking==0 ) {

                      					if (current==null) {
                      						current = createModelElement(grammarAccess.getPrimitiveTypeRule());
                      					}
                      					setWithLastConsumed(current, "name", lv_name_0_8, null);
                      				
                    }

                    }
                    break;
                case 9 :
                    // InternalClassDiagram.g:1424:5: lv_name_0_9= 'int'
                    {
                    lv_name_0_9=(Token)match(input,48,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      					newLeafNode(lv_name_0_9, grammarAccess.getPrimitiveTypeAccess().getNameIntKeyword_0_8());
                      				
                    }
                    if ( state.backtracking==0 ) {

                      					if (current==null) {
                      						current = createModelElement(grammarAccess.getPrimitiveTypeRule());
                      					}
                      					setWithLastConsumed(current, "name", lv_name_0_9, null);
                      				
                    }

                    }
                    break;
                case 10 :
                    // InternalClassDiagram.g:1435:5: lv_name_0_10= 'boolean'
                    {
                    lv_name_0_10=(Token)match(input,49,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      					newLeafNode(lv_name_0_10, grammarAccess.getPrimitiveTypeAccess().getNameBooleanKeyword_0_9());
                      				
                    }
                    if ( state.backtracking==0 ) {

                      					if (current==null) {
                      						current = createModelElement(grammarAccess.getPrimitiveTypeRule());
                      					}
                      					setWithLastConsumed(current, "name", lv_name_0_10, null);
                      				
                    }

                    }
                    break;
                case 11 :
                    // InternalClassDiagram.g:1446:5: lv_name_0_11= 'double'
                    {
                    lv_name_0_11=(Token)match(input,50,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      					newLeafNode(lv_name_0_11, grammarAccess.getPrimitiveTypeAccess().getNameDoubleKeyword_0_10());
                      				
                    }
                    if ( state.backtracking==0 ) {

                      					if (current==null) {
                      						current = createModelElement(grammarAccess.getPrimitiveTypeRule());
                      					}
                      					setWithLastConsumed(current, "name", lv_name_0_11, null);
                      				
                    }

                    }
                    break;

            }


            }


            }


            }

            if ( state.backtracking==0 ) {

              	leaveRule();

            }
        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "rulePrimitiveType"


    // $ANTLR start "entryRuleReferType"
    // InternalClassDiagram.g:1462:1: entryRuleReferType returns [EObject current=null] : iv_ruleReferType= ruleReferType EOF ;
    public final EObject entryRuleReferType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleReferType = null;


        try {
            // InternalClassDiagram.g:1462:50: (iv_ruleReferType= ruleReferType EOF )
            // InternalClassDiagram.g:1463:2: iv_ruleReferType= ruleReferType EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getReferTypeRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleReferType=ruleReferType();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleReferType; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleReferType"


    // $ANTLR start "ruleReferType"
    // InternalClassDiagram.g:1469:1: ruleReferType returns [EObject current=null] : ( (lv_name_0_0= RULE_ID ) ) ;
    public final EObject ruleReferType() throws RecognitionException {
        EObject current = null;

        Token lv_name_0_0=null;


        	enterRule();

        try {
            // InternalClassDiagram.g:1475:2: ( ( (lv_name_0_0= RULE_ID ) ) )
            // InternalClassDiagram.g:1476:2: ( (lv_name_0_0= RULE_ID ) )
            {
            // InternalClassDiagram.g:1476:2: ( (lv_name_0_0= RULE_ID ) )
            // InternalClassDiagram.g:1477:3: (lv_name_0_0= RULE_ID )
            {
            // InternalClassDiagram.g:1477:3: (lv_name_0_0= RULE_ID )
            // InternalClassDiagram.g:1478:4: lv_name_0_0= RULE_ID
            {
            lv_name_0_0=(Token)match(input,RULE_ID,FOLLOW_2); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              				newLeafNode(lv_name_0_0, grammarAccess.getReferTypeAccess().getNameIDTerminalRuleCall_0());
              			
            }
            if ( state.backtracking==0 ) {

              				if (current==null) {
              					current = createModelElement(grammarAccess.getReferTypeRule());
              				}
              				setWithLastConsumed(
              					current,
              					"name",
              					lv_name_0_0,
              					"org.eclipse.xtext.common.Terminals.ID");
              			
            }

            }


            }


            }

            if ( state.backtracking==0 ) {

              	leaveRule();

            }
        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleReferType"


    // $ANTLR start "entryRuleEnumItem"
    // InternalClassDiagram.g:1497:1: entryRuleEnumItem returns [EObject current=null] : iv_ruleEnumItem= ruleEnumItem EOF ;
    public final EObject entryRuleEnumItem() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleEnumItem = null;


        try {
            // InternalClassDiagram.g:1497:49: (iv_ruleEnumItem= ruleEnumItem EOF )
            // InternalClassDiagram.g:1498:2: iv_ruleEnumItem= ruleEnumItem EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getEnumItemRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleEnumItem=ruleEnumItem();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleEnumItem; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleEnumItem"


    // $ANTLR start "ruleEnumItem"
    // InternalClassDiagram.g:1504:1: ruleEnumItem returns [EObject current=null] : ( (lv_name_0_0= ruleSimpleName ) ) ;
    public final EObject ruleEnumItem() throws RecognitionException {
        EObject current = null;

        AntlrDatatypeRuleToken lv_name_0_0 = null;



        	enterRule();

        try {
            // InternalClassDiagram.g:1510:2: ( ( (lv_name_0_0= ruleSimpleName ) ) )
            // InternalClassDiagram.g:1511:2: ( (lv_name_0_0= ruleSimpleName ) )
            {
            // InternalClassDiagram.g:1511:2: ( (lv_name_0_0= ruleSimpleName ) )
            // InternalClassDiagram.g:1512:3: (lv_name_0_0= ruleSimpleName )
            {
            // InternalClassDiagram.g:1512:3: (lv_name_0_0= ruleSimpleName )
            // InternalClassDiagram.g:1513:4: lv_name_0_0= ruleSimpleName
            {
            if ( state.backtracking==0 ) {

              				newCompositeNode(grammarAccess.getEnumItemAccess().getNameSimpleNameParserRuleCall_0());
              			
            }
            pushFollow(FOLLOW_2);
            lv_name_0_0=ruleSimpleName();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              				if (current==null) {
              					current = createModelElementForParent(grammarAccess.getEnumItemRule());
              				}
              				set(
              					current,
              					"name",
              					lv_name_0_0,
              					"com.rm2pt.rapidood.cd.ClassDiagram.SimpleName");
              				afterParserOrEnumRuleCall();
              			
            }

            }


            }


            }

            if ( state.backtracking==0 ) {

              	leaveRule();

            }
        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleEnumItem"


    // $ANTLR start "entryRuleEnum"
    // InternalClassDiagram.g:1533:1: entryRuleEnum returns [EObject current=null] : iv_ruleEnum= ruleEnum EOF ;
    public final EObject entryRuleEnum() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleEnum = null;


        try {
            // InternalClassDiagram.g:1533:45: (iv_ruleEnum= ruleEnum EOF )
            // InternalClassDiagram.g:1534:2: iv_ruleEnum= ruleEnum EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getEnumRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleEnum=ruleEnum();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleEnum; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleEnum"


    // $ANTLR start "ruleEnum"
    // InternalClassDiagram.g:1540:1: ruleEnum returns [EObject current=null] : ( () otherlv_1= 'enum' ( (lv_name_2_0= ruleSimpleName ) ) otherlv_3= '{' ( ( (lv_values_4_0= ruleEnumItem ) ) ( (lv_values_5_0= ruleEnumItem ) )* ) otherlv_6= '}' ) ;
    public final EObject ruleEnum() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_3=null;
        Token otherlv_6=null;
        AntlrDatatypeRuleToken lv_name_2_0 = null;

        EObject lv_values_4_0 = null;

        EObject lv_values_5_0 = null;



        	enterRule();

        try {
            // InternalClassDiagram.g:1546:2: ( ( () otherlv_1= 'enum' ( (lv_name_2_0= ruleSimpleName ) ) otherlv_3= '{' ( ( (lv_values_4_0= ruleEnumItem ) ) ( (lv_values_5_0= ruleEnumItem ) )* ) otherlv_6= '}' ) )
            // InternalClassDiagram.g:1547:2: ( () otherlv_1= 'enum' ( (lv_name_2_0= ruleSimpleName ) ) otherlv_3= '{' ( ( (lv_values_4_0= ruleEnumItem ) ) ( (lv_values_5_0= ruleEnumItem ) )* ) otherlv_6= '}' )
            {
            // InternalClassDiagram.g:1547:2: ( () otherlv_1= 'enum' ( (lv_name_2_0= ruleSimpleName ) ) otherlv_3= '{' ( ( (lv_values_4_0= ruleEnumItem ) ) ( (lv_values_5_0= ruleEnumItem ) )* ) otherlv_6= '}' )
            // InternalClassDiagram.g:1548:3: () otherlv_1= 'enum' ( (lv_name_2_0= ruleSimpleName ) ) otherlv_3= '{' ( ( (lv_values_4_0= ruleEnumItem ) ) ( (lv_values_5_0= ruleEnumItem ) )* ) otherlv_6= '}'
            {
            // InternalClassDiagram.g:1548:3: ()
            // InternalClassDiagram.g:1549:4: 
            {
            if ( state.backtracking==0 ) {

              				current = forceCreateModelElement(
              					grammarAccess.getEnumAccess().getEnumAction_0(),
              					current);
              			
            }

            }

            otherlv_1=(Token)match(input,51,FOLLOW_4); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_1, grammarAccess.getEnumAccess().getEnumKeyword_1());
              		
            }
            // InternalClassDiagram.g:1559:3: ( (lv_name_2_0= ruleSimpleName ) )
            // InternalClassDiagram.g:1560:4: (lv_name_2_0= ruleSimpleName )
            {
            // InternalClassDiagram.g:1560:4: (lv_name_2_0= ruleSimpleName )
            // InternalClassDiagram.g:1561:5: lv_name_2_0= ruleSimpleName
            {
            if ( state.backtracking==0 ) {

              					newCompositeNode(grammarAccess.getEnumAccess().getNameSimpleNameParserRuleCall_2_0());
              				
            }
            pushFollow(FOLLOW_5);
            lv_name_2_0=ruleSimpleName();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              					if (current==null) {
              						current = createModelElementForParent(grammarAccess.getEnumRule());
              					}
              					set(
              						current,
              						"name",
              						lv_name_2_0,
              						"com.rm2pt.rapidood.cd.ClassDiagram.SimpleName");
              					afterParserOrEnumRuleCall();
              				
            }

            }


            }

            otherlv_3=(Token)match(input,16,FOLLOW_4); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_3, grammarAccess.getEnumAccess().getLeftCurlyBracketKeyword_3());
              		
            }
            // InternalClassDiagram.g:1582:3: ( ( (lv_values_4_0= ruleEnumItem ) ) ( (lv_values_5_0= ruleEnumItem ) )* )
            // InternalClassDiagram.g:1583:4: ( (lv_values_4_0= ruleEnumItem ) ) ( (lv_values_5_0= ruleEnumItem ) )*
            {
            // InternalClassDiagram.g:1583:4: ( (lv_values_4_0= ruleEnumItem ) )
            // InternalClassDiagram.g:1584:5: (lv_values_4_0= ruleEnumItem )
            {
            // InternalClassDiagram.g:1584:5: (lv_values_4_0= ruleEnumItem )
            // InternalClassDiagram.g:1585:6: lv_values_4_0= ruleEnumItem
            {
            if ( state.backtracking==0 ) {

              						newCompositeNode(grammarAccess.getEnumAccess().getValuesEnumItemParserRuleCall_4_0_0());
              					
            }
            pushFollow(FOLLOW_22);
            lv_values_4_0=ruleEnumItem();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              						if (current==null) {
              							current = createModelElementForParent(grammarAccess.getEnumRule());
              						}
              						add(
              							current,
              							"values",
              							lv_values_4_0,
              							"com.rm2pt.rapidood.cd.ClassDiagram.EnumItem");
              						afterParserOrEnumRuleCall();
              					
            }

            }


            }

            // InternalClassDiagram.g:1602:4: ( (lv_values_5_0= ruleEnumItem ) )*
            loop30:
            do {
                int alt30=2;
                int LA30_0 = input.LA(1);

                if ( (LA30_0==RULE_ID) ) {
                    alt30=1;
                }


                switch (alt30) {
            	case 1 :
            	    // InternalClassDiagram.g:1603:5: (lv_values_5_0= ruleEnumItem )
            	    {
            	    // InternalClassDiagram.g:1603:5: (lv_values_5_0= ruleEnumItem )
            	    // InternalClassDiagram.g:1604:6: lv_values_5_0= ruleEnumItem
            	    {
            	    if ( state.backtracking==0 ) {

            	      						newCompositeNode(grammarAccess.getEnumAccess().getValuesEnumItemParserRuleCall_4_1_0());
            	      					
            	    }
            	    pushFollow(FOLLOW_22);
            	    lv_values_5_0=ruleEnumItem();

            	    state._fsp--;
            	    if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      						if (current==null) {
            	      							current = createModelElementForParent(grammarAccess.getEnumRule());
            	      						}
            	      						add(
            	      							current,
            	      							"values",
            	      							lv_values_5_0,
            	      							"com.rm2pt.rapidood.cd.ClassDiagram.EnumItem");
            	      						afterParserOrEnumRuleCall();
            	      					
            	    }

            	    }


            	    }
            	    break;

            	default :
            	    break loop30;
                }
            } while (true);


            }

            otherlv_6=(Token)match(input,17,FOLLOW_2); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_6, grammarAccess.getEnumAccess().getRightCurlyBracketKeyword_5());
              		
            }

            }


            }

            if ( state.backtracking==0 ) {

              	leaveRule();

            }
        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleEnum"

    // $ANTLR start synpred1_InternalClassDiagram
    public final void synpred1_InternalClassDiagram_fragment() throws RecognitionException {   
        // InternalClassDiagram.g:282:5: ( '.' )
        // InternalClassDiagram.g:282:6: '.'
        {
        match(input,18,FOLLOW_2); if (state.failed) return ;

        }
    }
    // $ANTLR end synpred1_InternalClassDiagram

    // Delegated rules

    public final boolean synpred1_InternalClassDiagram() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred1_InternalClassDiagram_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }


 

    public static final BitSet FOLLOW_1 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_2 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_3 = new BitSet(new long[]{0x000800000038C010L});
    public static final BitSet FOLLOW_4 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_5 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_6 = new BitSet(new long[]{0x00080000003A8010L});
    public static final BitSet FOLLOW_7 = new BitSet(new long[]{0x0000000000040002L});
    public static final BitSet FOLLOW_8 = new BitSet(new long[]{0x00000000F1420010L});
    public static final BitSet FOLLOW_9 = new BitSet(new long[]{0x0000000000080010L});
    public static final BitSet FOLLOW_10 = new BitSet(new long[]{0x0000000000400010L});
    public static final BitSet FOLLOW_11 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_12 = new BitSet(new long[]{0x0007FF0000000010L});
    public static final BitSet FOLLOW_13 = new BitSet(new long[]{0x0000000001400010L});
    public static final BitSet FOLLOW_14 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_15 = new BitSet(new long[]{0x0007FF0008000010L});
    public static final BitSet FOLLOW_16 = new BitSet(new long[]{0x000000000C000000L});
    public static final BitSet FOLLOW_17 = new BitSet(new long[]{0x000000FF20040020L});
    public static final BitSet FOLLOW_18 = new BitSet(new long[]{0x0000003F00000030L});
    public static final BitSet FOLLOW_19 = new BitSet(new long[]{0x0000000000000030L});
    public static final BitSet FOLLOW_20 = new BitSet(new long[]{0x0000000000800002L});
    public static final BitSet FOLLOW_21 = new BitSet(new long[]{0x0000000000000012L});
    public static final BitSet FOLLOW_22 = new BitSet(new long[]{0x0000000000020010L});

}