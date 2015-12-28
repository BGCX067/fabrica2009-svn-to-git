var freq=null;
var cur_e= new Object();
var lastq='';
var FORM_timer=0;

window.setInterval("check_form()", 1000);

var freq_busy = false;
var ajxCf =new Object();
var requestTimer;

try { freq = new XMLHttpRequest(); } catch (trymicrosoft) {
	try { freq = new ActiveXObject("Msxml2.XMLHTTP"); } catch (othermicrosoft) {
		try { freq = new ActiveXObject("Microsoft.XMLHTTP");
		} catch (failed) { freq = null; }
}}

function get_t() {
	var d= new Date();
	return Math.round(d.getTime()/1000);;
}


function hide_as () {
	cur_e.pointer = undefined;
	FORM_timer=0;
	lastq='';
	$("#"+cur_e.name+'_sug').slideUp('fast');
	$("#filler").empty();
}

function check_form() {

	if (
		( FORM_timer <0 )	
		||
		(FORM_timer>0 && (get_t()-3 >FORM_timer) )
	) {
		hide_as();
	}
}

var fready=false;
$(function () {fready=true });

function faj(e,url,event) {
	if (!fready) return;
	
    f_cerr(e);
	if (e.value.length<2) {
		FORM_timer = get_t();
		hide_as();
		return;
	}

	var charCode = event.keyCode;
	if ( (charCode == 40) || (charCode == 13) || (charCode ==27) || (charCode==38)) return;

	FORM_timer = 0;


	/* An space at the end */
	var etrim =  e.value.replace(/^\s+|\s+$/g, ''); 
/*console.log("etrim:"+etrim+":")*/;

	if (etrim==lastq) return;

	/*todo check change letters! */
	cur_e=e;
	lastq=etrim;
	path= url+encodeURIComponent(etrim);
/*console.log("RUNNING: path"+path);*/

	if (ajxCf[path]) {
/*console.log("Using cache for "+path);*/
		f_fill(ajxCf[path]);
	} 
	else {
		if (freq_busy) {
			freq.onreadystatechange = function () {freq.abort()};
			freq.abort();
			/*console.log("ABORT")*/;
		}
		/*console.log("new req"+path);*/
		freq.open("GET", path, true); /*will abort current reqs*/

		requestTimer = setTimeout(function() {
				freq.abort();
				/*console.log("TIMEOUT")*/;
				}, 4000); 


		freq.onreadystatechange = function () {
			if (!freq) return 0;
			if	(freq.readyState != 4) return 0; 
			freq_busy = false;
			try {
				if (freq.status != 200) return 0;
			} catch (error_gecko) { return 0; };

			if (freq.readyState == 4) {
				if (requestTimer!=-1) window.clearTimeout(requestTimer);

				ajxCf[path]=freq.responseText;
/*console.log("FILLOING path"+path);*/
				f_fill(freq.responseText);
			}
			return (false);
		};
		freq.send(null); 
		freq_busy = true;
	}
	return 1;
}

function fdo (e,event) {
	var charCode = event.keyCode;

/*console.log("CODE"+charCode); console.log( "LEN" + $("#filler > .fillit").length ); */
	var count = $("#filler > .fillit").length;
	if  ( ! count ) return;


/*dump(charCode + "\n");*/

	switch (charCode) {
		case 27:
		case 13:
			mo_cb2(e,e.pointer);

			FORM_timer = -1; 

			check_form();
			return 0;
		case 40:
/*dump(" down \n");*/
			if (e.pointer== undefined ) e.pointer =-1;
			e.pointer = (e.pointer+1) % count;
/*dump( e.pointer + " [pointer] \n");*/
			mo_cb2(e,e.pointer);
			return 0;
		case 38:
/*dump(" up \n");*/
			if (e.pointer== undefined ) e.pointer =0;
			e.pointer = e.pointer-1;
			if (e.pointer<0) e.pointer = count;
			mo_cb2(e,e.pointer);
			return 0;
	}
	return 1;
}

function dmout(e) {
	FORM_timer = get_t();
}


function mo_cb2 (cur_e,i) { 
	/*dump(' mb : ' + cur_e + ' i ' + i + '\n' );*/

	var idx;
	var h= $("#filler .fillit")
		.removeClass("sel")
		.eq(i)
		.addClass("sel")
		.each(function(){ if(this.id.match(/^filler_(\d+)/)) idx=RegExp.$1; })
		.html()
		;

	if (h && ! (document.getElementById("nofillit") ) ) {

	  	/* removes subtitles, later tags, and later, spaces*/
		cur_e.value = h
			.replace(/(<span class="subt".*)/ig,"")
			.replace(/(<([^>]+)>)/ig,"")
			.replace(/\s+/g,' ')
			;
	}

	FORM_timer = 0;
	cur_e.pointer = i;

	/* set numeric ID in hidden field */
	$("#" + cur_e.name+'_id').attr("value",idx);
}; 

function f_fill (txt) {

 	var e = $("#"+cur_e.name+'_sug');
/*console.log("SELECTOR",e);*/

	/* no suggestion box yet? create one */
	if ( e.size() == 0 ) {


		e= $( '<div id="'+cur_e.name+'_sug'+'">.</div>' ).appendTo('body');

		var off = $(cur_e).offset();
		var h = $(cur_e).height();
		var w = $(cur_e).width();

 		e.addClass("sugg").css({
			position:'absolute',
			display:'block',
			top: ( off.top  + h + 2) +"px", 
			left:off.left +"px",
			width:w +"px",
			visibility:'hidden'
		});
	}
	else {
		e.slideDown('fast');
	};



	if ( (!txt) || txt.match(/^\s*$/)  ) {
		e.html('').css({display:"none"});
	}
	else {
		e.html(txt) .css({ visibility:'visible' });
		cur_e.pointer=undefined;
		$(".fillit")
			.hover( 
				function () { 
					FORM_timer = 0;
					var i = $(".fillit").index(this);
					mo_cb2(cur_e,i); 
				},
				function () { 
					$(this).removeClass("sel"); 
					dmout();
				}	
			)
			.click( function () { FORM_timer = -1; check_form(); })
			;
	}
	 
	$("#"+cur_e.name+'_id').attr( {value:''} );
	return 1;
}
function sc2(e) {
	scrollTo(e.offsetLeft, e.offsetTop-50);
}
function f_emp(e){
	
}

function f_error(e,errstr){
	if (!e) return;
	e.focus(); 
	e.style.backgroundColor='yellow';
	if (i=document.getElementById(e.name+'_err')) {
		i.innerHTML=errstr; 
    };
	sc2(e);
}
function f_nr(e,min){ 
	e.value= e.value.replace(/^\s+|\s+$/g, ''); 
	if (e.value == "") { 
		f_error(e,err['fill_here']);
		return (false); 
	} else if (! e.value.match(/^[\d.,]+$/)) {
		f_error(e,err['number_here']);
		return (false); 
	}
    f_cerr(e);
	return (true); 
}
function f_str(e,min,allowe){ 
	e.value= e.value.replace(/^\s+|\s+$/g, ''); 
	if (allowe && (e.value.length==0) ) return (true);
	if (e.value == "") { 
		f_error(e,err['fill_here']);
		return (false); 
	} else if ((min) && (e.value.length<min)) {
		f_error(e,err['too_small'].replace(/%d/,min).replace(/%d/,e.value.length));
		return (false); 
	}
    f_cerr(e);
	return (true); 
}

function f_select(e,unsel){ 
	if (e.value == unsel) { 
		f_error(e,err['bad_options']);
		return (false); 
	}
    f_cerr(e);
	return (true); 
}
function f_disable(e){ 
	if (!e) return;
	e.value=err['sending'];
	e.style.color='#638aa7';
	e.disabled=true;
	return (true); 
}
function f_conf(e){
    var agree=confirm("Seguro?");
    if (agree) return true ;
    else return false ;
}
function f_radio(e) {
	var myOption = -1;
	for (i=0; i < e.length; i++) {
		if (e[i].checked) {
			myOption = i;
		}
	}
	if (myOption == -1) {
		if (i=document.getElementById(e[0].name+'_err')) {
            if ( e[0].parentNode.parentNode.parentNode) {
    			e[0].parentNode.parentNode.parentNode.style.backgroundColor='yellow';
            }
			i.innerHTML=err['bad_options'];
		};
		sc2(i);
		return false;
	} else {
        f_cerr(e);
	}
	return (true);
}

function f_diff(e1,e2){ 
	if (e1.value != e2.value) { 
		e1.style.backgroundColor='yellow';
		e2.style.backgroundColor='yellow';
		e1.value='';
		e2.value='';
		if (i=document.getElementById(e1.name+'_err')) {
			i.innerHTML=err['passwords_differ'];
		};
		e1.focus(); 
		sc2(e1);
		return (false);
	} 
	e1.style.backgroundColor='white';
	e2.style.backgroundColor='white';
	return (true); 
}
function f_email (e,allowe) {
 	var str=e.value;
	if (allowe && (str.length==0) ) return (true);
	if ((str.indexOf(".") > 2) && (str.indexOf("@") > 0)) {
	   	return (true);
   	}
	f_error(e,err['bad_email'].replace(/%s/,str));
	sc2(e);
   return (false);
}

function f_memail (e,allowe) {
 	var str=e.value;
	if (allowe && (str.length==0) ) return (true);

	var emails=str.split(",");
	var i=0;
	while (i < emails.length)
	{
		if (!((emails[i].indexOf(".") > 2) && (emails[i].indexOf("@") > 0))) {
			f_error(e,err['bad_email'].replace(/%s/,emails[i]));
			sc2(e);
			return (false);
		}

  		i+=1;
  	}
   	return (true);
}

function f_cerr(e) {
    var i;
	if (!e) return;


	if (i=document.getElementById(e.name+'_err')) {
        if ( i.innerHTML && i.innerHTML != '' ) i.innerHTML='';
	}


    if (e.style) 
    	e.style.backgroundColor='white';
    else if (e.parentNode && e.parentNode.style) 
    	e.parentNode.style.backgroundColor='white';
    else if (e.parentNode && e.parentNode.parentNode.style) 
    	e.parentNode.parentNode.style.backgroundColor='white';
}


/*
 * A JavaScript implementation of the RSA Data Security, Inc. MD5 Message
 * Digest Algorithm, as defined in RFC 1321.
 * Version 2.1 Copyright (C) Paul Johnston 1999 - 2002.
 * Other contributors: Greg Holt, Andrew Kepert, Ydnar, Lostinet
 * Distributed under the BSD License
 * See http://pajhome.org.uk/crypt/md5 for more info.
 */

function str2hex_md5(e,e2)
{
	e2.value = hex_md5(e.value);
	e.value='';
	return true;
}
var hexcase = 0;  /* hex output format. 0 - lowercase; 1 - uppercase        */
var b64pad  = ""; /* base-64 pad character. "=" for strict RFC compliance   */
var chrsz   = 8;  /* bits per input character. 8 - ASCII; 16 - Unicode      */
function hex_md5(s){ return binl2hex(core_md5(str2binl(s), s.length * chrsz));}
function core_md5(x, len)
{
  /* append padding */
  x[len >> 5] |= 0x80 << ((len) % 32);
  x[(((len + 64) >>> 9) << 4) + 14] = len;

  var a =  1732584193;
  var b = -271733879;
  var c = -1732584194;
  var d =  271733878;

  for(var i = 0; i < x.length; i += 16)
  {
    var olda = a;
    var oldb = b;
    var oldc = c;
    var oldd = d;

    a = md5_ff(a, b, c, d, x[i+ 0], 7 , -680876936);
    d = md5_ff(d, a, b, c, x[i+ 1], 12, -389564586);
    c = md5_ff(c, d, a, b, x[i+ 2], 17,  606105819);
    b = md5_ff(b, c, d, a, x[i+ 3], 22, -1044525330);
    a = md5_ff(a, b, c, d, x[i+ 4], 7 , -176418897);
    d = md5_ff(d, a, b, c, x[i+ 5], 12,  1200080426);
    c = md5_ff(c, d, a, b, x[i+ 6], 17, -1473231341);
    b = md5_ff(b, c, d, a, x[i+ 7], 22, -45705983);
    a = md5_ff(a, b, c, d, x[i+ 8], 7 ,  1770035416);
    d = md5_ff(d, a, b, c, x[i+ 9], 12, -1958414417);
    c = md5_ff(c, d, a, b, x[i+10], 17, -42063);
    b = md5_ff(b, c, d, a, x[i+11], 22, -1990404162);
    a = md5_ff(a, b, c, d, x[i+12], 7 ,  1804603682);
    d = md5_ff(d, a, b, c, x[i+13], 12, -40341101);
    c = md5_ff(c, d, a, b, x[i+14], 17, -1502002290);
    b = md5_ff(b, c, d, a, x[i+15], 22,  1236535329);

    a = md5_gg(a, b, c, d, x[i+ 1], 5 , -165796510);
    d = md5_gg(d, a, b, c, x[i+ 6], 9 , -1069501632);
    c = md5_gg(c, d, a, b, x[i+11], 14,  643717713);
    b = md5_gg(b, c, d, a, x[i+ 0], 20, -373897302);
    a = md5_gg(a, b, c, d, x[i+ 5], 5 , -701558691);
    d = md5_gg(d, a, b, c, x[i+10], 9 ,  38016083);
    c = md5_gg(c, d, a, b, x[i+15], 14, -660478335);
    b = md5_gg(b, c, d, a, x[i+ 4], 20, -405537848);
    a = md5_gg(a, b, c, d, x[i+ 9], 5 ,  568446438);
    d = md5_gg(d, a, b, c, x[i+14], 9 , -1019803690);
    c = md5_gg(c, d, a, b, x[i+ 3], 14, -187363961);
    b = md5_gg(b, c, d, a, x[i+ 8], 20,  1163531501);
    a = md5_gg(a, b, c, d, x[i+13], 5 , -1444681467);
    d = md5_gg(d, a, b, c, x[i+ 2], 9 , -51403784);
    c = md5_gg(c, d, a, b, x[i+ 7], 14,  1735328473);
    b = md5_gg(b, c, d, a, x[i+12], 20, -1926607734);

    a = md5_hh(a, b, c, d, x[i+ 5], 4 , -378558);
    d = md5_hh(d, a, b, c, x[i+ 8], 11, -2022574463);
    c = md5_hh(c, d, a, b, x[i+11], 16,  1839030562);
    b = md5_hh(b, c, d, a, x[i+14], 23, -35309556);
    a = md5_hh(a, b, c, d, x[i+ 1], 4 , -1530992060);
    d = md5_hh(d, a, b, c, x[i+ 4], 11,  1272893353);
    c = md5_hh(c, d, a, b, x[i+ 7], 16, -155497632);
    b = md5_hh(b, c, d, a, x[i+10], 23, -1094730640);
    a = md5_hh(a, b, c, d, x[i+13], 4 ,  681279174);
    d = md5_hh(d, a, b, c, x[i+ 0], 11, -358537222);
    c = md5_hh(c, d, a, b, x[i+ 3], 16, -722521979);
    b = md5_hh(b, c, d, a, x[i+ 6], 23,  76029189);
    a = md5_hh(a, b, c, d, x[i+ 9], 4 , -640364487);
    d = md5_hh(d, a, b, c, x[i+12], 11, -421815835);
    c = md5_hh(c, d, a, b, x[i+15], 16,  530742520);
    b = md5_hh(b, c, d, a, x[i+ 2], 23, -995338651);

    a = md5_ii(a, b, c, d, x[i+ 0], 6 , -198630844);
    d = md5_ii(d, a, b, c, x[i+ 7], 10,  1126891415);
    c = md5_ii(c, d, a, b, x[i+14], 15, -1416354905);
    b = md5_ii(b, c, d, a, x[i+ 5], 21, -57434055);
    a = md5_ii(a, b, c, d, x[i+12], 6 ,  1700485571);
    d = md5_ii(d, a, b, c, x[i+ 3], 10, -1894986606);
    c = md5_ii(c, d, a, b, x[i+10], 15, -1051523);
    b = md5_ii(b, c, d, a, x[i+ 1], 21, -2054922799);
    a = md5_ii(a, b, c, d, x[i+ 8], 6 ,  1873313359);
    d = md5_ii(d, a, b, c, x[i+15], 10, -30611744);
    c = md5_ii(c, d, a, b, x[i+ 6], 15, -1560198380);
    b = md5_ii(b, c, d, a, x[i+13], 21,  1309151649);
    a = md5_ii(a, b, c, d, x[i+ 4], 6 , -145523070);
    d = md5_ii(d, a, b, c, x[i+11], 10, -1120210379);
    c = md5_ii(c, d, a, b, x[i+ 2], 15,  718787259);
    b = md5_ii(b, c, d, a, x[i+ 9], 21, -343485551);

    a = safe_add(a, olda);
    b = safe_add(b, oldb);
    c = safe_add(c, oldc);
    d = safe_add(d, oldd);
  }
  return Array(a, b, c, d);
}
function md5_cmn(q, a, b, x, s, t)
{
  return safe_add(bit_rol(safe_add(safe_add(a, q), safe_add(x, t)), s),b);
}
function md5_ff(a, b, c, d, x, s, t)
{
  return md5_cmn((b & c) | ((~b) & d), a, b, x, s, t);
}
function md5_gg(a, b, c, d, x, s, t)
{
  return md5_cmn((b & d) | (c & (~d)), a, b, x, s, t);
}
function md5_hh(a, b, c, d, x, s, t)
{
  return md5_cmn(b ^ c ^ d, a, b, x, s, t);
}
function md5_ii(a, b, c, d, x, s, t)
{
  return md5_cmn(c ^ (b | (~d)), a, b, x, s, t);
}
function core_hmac_md5(key, data)
{
  var bkey = str2binl(key);
  if(bkey.length > 16) bkey = core_md5(bkey, key.length * chrsz);

  var ipad = Array(16), opad = Array(16);
  for(var i = 0; i < 16; i++)
  {
    ipad[i] = bkey[i] ^ 0x36363636;
    opad[i] = bkey[i] ^ 0x5C5C5C5C;
  }

  var hash = core_md5(ipad.concat(str2binl(data)), 512 + data.length * chrsz);
  return core_md5(opad.concat(hash), 512 + 128);
}
function safe_add(x, y)
{
  var lsw = (x & 0xFFFF) + (y & 0xFFFF);
  var msw = (x >> 16) + (y >> 16) + (lsw >> 16);
  return (msw << 16) | (lsw & 0xFFFF);
}
function bit_rol(num, cnt)
{
  return (num << cnt) | (num >>> (32 - cnt));
}
function str2binl(str)
{
  var bin = Array();
  var mask = (1 << chrsz) - 1;
  for(var i = 0; i < str.length * chrsz; i += chrsz)
    bin[i>>5] |= (str.charCodeAt(i / chrsz) & mask) << (i%32);
  return bin;
}
function binl2str(bin)
{
  var str = "";
  var mask = (1 << chrsz) - 1;
  for(var i = 0; i < bin.length * 32; i += chrsz)
    str += String.fromCharCode((bin[i>>5] >>> (i % 32)) & mask);
  return str;
}
function binl2hex(binarray)
{
  var hex_tab = hexcase ? "0123456789ABCDEF" : "0123456789abcdef";
  var str = "";
  for(var i = 0; i < binarray.length * 4; i++)
  {
    str += hex_tab.charAt((binarray[i>>2] >> ((i%4)*8+4)) & 0xF) +
           hex_tab.charAt((binarray[i>>2] >> ((i%4)*8  )) & 0xF);
  }
  return str;
}
function binl2b64(binarray)
{
  var tab = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";
  var str = "";
  for(var i = 0; i < binarray.length * 4; i += 3)
  {
    var triplet = (((binarray[i   >> 2] >> 8 * ( i   %4)) & 0xFF) << 16)
                | (((binarray[i+1 >> 2] >> 8 * ((i+1)%4)) & 0xFF) << 8 )
                |  ((binarray[i+2 >> 2] >> 8 * ((i+2)%4)) & 0xFF);
    for(var j = 0; j < 4; j++)
    {
      if(i * 8 + j * 6 > binarray.length * 32) str += b64pad;
      else str += tab.charAt((triplet >> 6*(3-j)) & 0x3F);
    }
  }
  return str;
}

function mce(id) {
	var elm = document.getElementById(id);
	if (tinyMCE.getInstanceById(id) == null)
		tinyMCE.execCommand('mceAddControl', false, id);
	else
		tinyMCE.execCommand('mceRemoveControl', false, id);

	return false;
}


$(function() {
    $(".cform input[@type='text'], .cform input[@type='file'], .cform input[@type='radiobox'], .cform input[@type='password'], .cform input[@type='checkbox'], .cform select , .cform textarea").focus(function() { 
        $(".form_value").parent().removeClass('focus') 
        $(this).parent().parent().addClass('focus') 
    });
});

