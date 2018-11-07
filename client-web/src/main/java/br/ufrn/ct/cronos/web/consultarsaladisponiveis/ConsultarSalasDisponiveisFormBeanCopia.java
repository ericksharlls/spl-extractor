/*     */ package br.ufrn.ct.cronos.web.consultarsaladisponiveis;
/*     */ 
/*     */ import br.ufrn.ct.cronos.alocarturma.vo.AlocarTurma;
/*     */ import br.ufrn.ct.cronos.consultarsalasdisponiveis.vo.ConsultarSalasDisponiveis;
/*     */ import br.ufrn.ct.cronos.consultarsalasdisponiveis.vo.DadosConsultarSalasDisponiveis;
/*     */ import br.ufrn.ct.cronos.consultarsalasdisponiveis.vo.RespostaConsultarSalasDisponiveis;
/*     */ import dev.home.componente.service.Service;
/*     */ import dev.home.componente.service.excecao.NegocioException;
/*     */ import dev.home.componente.service.util.ServiceUtils;
/*     */ import dev.home.componente.web.infra.AbstractConsultarFormBean;
/*     */ import dev.home.componente.web.infra.paginacao.PaginacaoDataModel;
/*     */ import java.io.PrintStream;
/*     */ import javax.faces.component.UIComponent;
/*     */ import javax.faces.component.UIParameter;
/*     */ import javax.faces.event.ActionEvent;
/*     */ import javax.faces.model.DataModel;
/*     */ import org.richfaces.component.html.HtmlExtendedDataTable;
/*     */ 
/*     */ public class ConsultarSalasDisponiveisFormBeanCopia extends AbstractConsultarFormBean
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private Long idTurma;
/*     */   private Long idSala;
/*     */   private DadosConsultarSalasDisponiveis sala;
/*     */   private HtmlExtendedDataTable tabelaLegal;
/*  36 */   private DataModel dataModelLegal = null;
/*     */   private Long idLong;
/*     */ 
/*     */   public DataModel getDados()
/*     */     throws NegocioException
/*     */   {
/*  45 */     ConsultarSalasDisponiveis solicitacao = new ConsultarSalasDisponiveis();
/*  46 */     solicitacao.setIdTurma(getIdTurma());
/*  47 */     Service service = ServiceUtils.getService(solicitacao.getClass().getSimpleName());
/*  48 */     RespostaConsultarSalasDisponiveis resposta = (RespostaConsultarSalasDisponiveis)service.executa(solicitacao);
/*  49 */     return new PaginacaoDataModel(resposta.getDados(), resposta.getTotalNumeroLinhas().intValue());
/*     */   }
/*     */ 
/*     */   public String alocar() throws NegocioException {
/*  53 */     AlocarTurma solicitacao = new AlocarTurma();
/*  54 */     solicitacao.setIdTurma(this.idTurma);
/*  55 */     solicitacao.setIdSala(this.idSala);
/*  56 */     Service service = ServiceUtils.getService(solicitacao.getClass().getSimpleName());
/*  57 */     service.executa(solicitacao);
/*     */ 
/*  59 */     return null;
/*     */   }
/*     */ 
/*     */   public void selecionar(ActionEvent event)
/*     */   {
/*  72 */     UIComponent link = event.getComponent();
/*  73 */     UIParameter param = (UIParameter)link.findComponent("testeId");
/*     */ 
/*  79 */     System.out.println("##### IDPOS(2): '" + this.idLong + "'");
/*     */   }
/*     */ 
/*     */   public int getLinhas()
/*     */   {
/*  94 */     return 0;
/*     */   }
/*     */ 
/*     */   public void setSala(DadosConsultarSalasDisponiveis sala) {
/*  98 */     this.sala = sala;
/*     */   }
/*     */ 
/*     */   public DadosConsultarSalasDisponiveis getSala() {
/* 102 */     return this.sala;
/*     */   }
/*     */ 
/*     */   public void setIdTurma(Long idTurma) {
/* 106 */     this.idTurma = idTurma;
/*     */   }
/*     */ 
/*     */   public Long getIdTurma() {
/* 110 */     return this.idTurma;
/*     */   }
/*     */ 
/*     */   public HtmlExtendedDataTable getTabelaLegal() {
/* 114 */     return this.tabelaLegal;
/*     */   }
/*     */ 
/*     */   public void setTabelaLegal(HtmlExtendedDataTable tabelaLegal) {
/* 118 */     this.tabelaLegal = tabelaLegal;
/*     */   }
/*     */ 
/*     */   public void setDataModelLegal(DataModel dataModelLegal) {
/* 122 */     this.dataModelLegal = dataModelLegal;
/*     */   }
/*     */ 
/*     */   public DataModel getDataModelLegal() {
/* 126 */     return this.dataModelLegal;
/*     */   }
/*     */ 
/*     */   public void setIdSala(Long idSala) {
/* 130 */     this.idSala = idSala;
/*     */   }
/*     */ 
/*     */   public Long getIdSala() {
/* 134 */     return this.idSala;
/*     */   }
/*     */ 
/*     */   public void setIdLong(Long idLong) {
/* 138 */     this.idLong = idLong;
/*     */   }
/*     */ 
/*     */   public Long getIdLong() {
/* 142 */     return this.idLong;
/*     */   }
/*     */ }

/* Location:           C:\Users\Erick\Documents\componentesCronosAtualizados\cronos-client-web-0.0.1.0-SNAPSHOT\
 * Qualified Name:     br.ufrn.ct.cronos.web.consultarsaladisponiveis.ConsultarSalasDisponiveisFormBeanCopia
 * JD-Core Version:    0.6.2
 */