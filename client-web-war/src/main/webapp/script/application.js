jQuery.noConflict();
(function($) {
	$(function() {
		$("#loginForm\\:usuario").focus();
		$("#statusForm\\:nome").focus();
		$("#problemaForm\\:nome").focus();
		$("#usuarioForm\\:matricula").focus();
		$("#problemaForm\\:estimativaResolucao").mask("99:99");
	});
})(jQuery);

function backspace(evt) {
	var evento = (window.event)?event.srcElement.tagName:evt.target.tagName;
	var tecla = (window.event)?event.keyCode:evt.which;
	var backspace = 8;
	if (backspace == tecla && evento != "INPUT" && evento != "TEXTAREA") {
		return false;
	}
}

function enter(obj, e, formId) {
	var keynum;
	var keychar;  
	var valida; 
	if(window.event) {
	    keynum = e.keyCode;
	}
	else if(e.which) {
		keynum = e.which;
	}
	keychar = String.fromCharCode(keynum);
	numcheck = /\d/;
	if( keynum == 13) {
	    valida = true;
	    document.getElementById(formId).click();
	}
	return valida;
}