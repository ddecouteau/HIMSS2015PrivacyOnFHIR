$wnd.gov_va_ehtac_myappsonfhir_gwt_HealthElementsForPatientWidgetSet.runAsyncCallback13("function tEc(){}\nfunction vEc(){}\nfunction xEc(){}\nfunction Rnd(){x3b.call(this)}\nfunction e7b(a,b){return dF(a.H.bn(b),2)}\nfunction f7b(a){return a.e.q+'themes/'+a.P.b}\nfunction zEd(){_2b.call(this);this.I=dde;this.b=new qPd}\nfunction dSc(c,a){var b=c;a.notifyChildrenOfSizeChange=CSd(function(){b.ol()})}\nfunction lSc(b){try{b!=null&&eval('{ var document = $doc; var window = $wnd; '+b+'}')}catch(a){}}\nfunction aSc(b){if(b&&b.iLayoutJS){try{b.iLayoutJS();return true}catch(a){return false}}else{return false}}\nfunction _Rc(a,b){var c,d;for(c=iMd(DKd(a.g));c.b.Zf();){d=dF(nMd(c),2);if(jF(a.g.bn(d))===jF(b)){return d}}return null}\nfunction eSc(a,b){var c,d;d=_Rc(a,b);d!=null&&a.g.en(d);c=dF(a.b.bn(b),305);if(c){a.b.en(b);return Qpb(a,c)}else if(b){return Qpb(a,b)}return false}\nfunction bSc(a){var b,c,d;d=(alb(),a.ac).getElementsByTagName('IMG');for(b=0;b<d.length;b++){c=d[b];$kb.Ve(c,hWd)}}\nfunction fSc(a,b){var c,d,e;if((ll(),b).hasAttribute(U8d)){e=rl(b,U8d);a.f.dn(e,b);Yk(b,'')}else{d=(alb(),mnb(b));for(c=0;c<d;c++){fSc(a,lnb(b,c))}}}\nfunction gSc(a,b,c){var d,e;if(!b){return}d=eF(a.f.bn(c));if(!d&&a.e){throw new uId('No location '+c+B0d)}e=dF(a.g.bn(c),20);if(e==b){return}!!e&&eSc(a,e);a.e||(d=(alb(),a.ac));Gpb(a,b,(alb(),d));a.g.dn(c,b)}\nfunction hSc(a,b){var c,d,e;d=b.Of();e=dF(a.b.bn(d),305);if(jgc(b.Rg())){if(!e){c=_Rc(a,d);Qpb(a,d);e=new pgc(b,a.c);Fpb(a,e,eF(a.f.bn(c)));a.b.dn(d,e)}egc(e.b)}else{if(e){c=_Rc(a,d);Qpb(a,e);Fpb(a,d,eF(a.f.bn(c)));a.b.en(d)}}}\nfunction pEc(c){var d={setter:function(a,b){a.c=b},getter:function(a){return a.c}};c.Kk(Obb,_ce,d);var d={setter:function(a,b){a.b=b},getter:function(a){return a.b}};c.Kk(Obb,ade,d);var d={setter:function(a,b){a.d=b},getter:function(a){return a.d}};c.Kk(Obb,bde,d)}\nfunction iSc(){var a;Rpb.call(this);this.f=new qPd;this.g=new qPd;this.b=new qPd;this.j='';this.e=false;Cob(this,(alb(),cn($doc)));a=this.ac.style;ap(a,q$d,(hp(),gUd));a[y0d]=0+(Ts(),rUd);a[T0d]=UWd;(C9b(),!B9b&&(B9b=new T9b),C9b(),B9b).b.i&&ap(a,NTd,(Vr(),GWd));Wk(this.ac,dde);Zob(this.ac,D7d,true)}\nfunction Qnd(a){var b,c;if(a.b){return}c=(!a.G&&(a.G=DDb(a)),dF(dF(dF(a.G,5),41),175)).d;b=(!a.G&&(a.G=DDb(a)),dF(dF(dF(a.G,5),41),175)).c;if(c!=null){b=e7b(a.v,'layouts/'+c+'.html');b==null&&Yk(rob(dF(jEb(a),84)),'<em>Layout file layouts/'+c+'.html is missing. Components will be drawn for debug purposes.<\\/em>')}b!=null&&cSc(dF(jEb(a),84),b,f7b(a.v));a.b=true}\nfunction $Rc(a,b){var c,d,e,f,g,i,j,k;b=EJd(b,'_UID_',a.i+'__');a.j='';d=0;f=b.toLowerCase();i='';j=f.indexOf('<script',0);while(j>0){i+=JJd(b,d,j);j=f.indexOf('>',j);e=f.indexOf('<\\/script>',j);a.j+=JJd(b,j+1,e)+';';g=d=e+9;j=f.indexOf('<script',g)}i+=IJd(b,d);f=i.toLowerCase();k=f.indexOf('<body');if(k<0){i=i}else{k=f.indexOf('>',k)+1;c=f.indexOf('<\\/body>',k);c>k?(i=JJd(i,k,c)):(i=IJd(i,k))}return i}\nfunction cSc(a,b,c){var d;b=$Rc(a,b);d=Yec(c+'/layouts/');b=EJd(b,'<((?:img)|(?:IMG))\\\\s([^>]*)src=\"((?![a-z]+:)[^/][^\"]+)\"',cde+d+'$3\"');b=EJd(b,'<((?:img)|(?:IMG))\\\\s([^>]*)src=[^\"]((?![a-z]+:)[^/][^ />]+)[ />]',cde+d+'$3\"');b=EJd(b,'(<[^>]+style=\"[^\"]*url\\\\()((?![a-z]+:)[^/][^\"]+)(\\\\)[^>]*>)','$1 '+d+'$2 $3');Yk((alb(),a.ac),b);a.f.wf();fSc(a,a.ac);bSc(a);a.d=ilb(a.ac);!a.d&&(a.d=a.ac);dSc(a,a.d);a.e=true}\nvar _ce='templateContents',ade='childLocations',bde='templateName',cde='<$1 $2src=\"',dde='v-customlayout';$gb(1022,1,V_d);_.vc=function sEc(){CJc(this.c,Obb,dbb);sJc(this.c,u3d,c8);vJc(this.c,g3,H3d,new tEc);vJc(this.c,c8,H3d,new vEc);vJc(this.c,Obb,H3d,new xEc);AJc(this.c,c8,oYd,new kJc(g3));AJc(this.c,c8,fYd,new kJc(Obb));pEc(this.c);yJc(this.c,Obb,_ce,new kJc(Ldb));yJc(this.c,Obb,ade,new lJc(I4d,UE(Ufb,b4d,8,0,[new kJc(Nab),new kJc(Ldb)])));yJc(this.c,Obb,bde,new kJc(Ldb));_rc((!Urc&&(Urc=new gsc),Urc),this.b.e)};$gb(1592,1,z6d,tEc);_.Ek=function uEc(a,b){return new iSc};$gb(1942,1,z6d,vEc);_.Ek=function wEc(a,b){return new Rnd};$gb(yVd,1,z6d,xEc);_.Ek=function yEc(a,b){return new zEd};$gb(84,871,{2411:1,2256:1,1559:1,2617:1,134:1,2494:1,2252:1,299:1,300:1,20:1,84:1,210:1},iSc);_.vf=function jSc(a){throw new zKd};_.wf=function kSc(){Apb(this);this.g.wf();this.b.wf()};_.ol=function mSc(){};_.Qe=function nSc(a){kpb(this,a);if((alb(),Vmb(a))==hWd){Bfc(this,true);Umb(a,true)}};_.rf=function oSc(){lpb(this);!!this.d&&(this.d.notifyChildrenOfSizeChange=null,undefined)};_.Me=function pSc(a){return eSc(this,a)};_.jf=function qSc(a){Wk((alb(),this.ac),a);Zob(this.ac,D7d,true)};_.e=false;$gb(1402,1403,{430:1,7:1,645:1,78:1,306:1,69:1,1644:1,178:1,219:1,90:1,335:1,153:1,3:1},Rnd);_.Rg=function Snd(){return !this.G&&(this.G=DDb(this)),dF(dF(dF(this.G,5),41),175)};_.Dg=function Tnd(){return !this.G&&(this.G=DDb(this)),dF(dF(dF(this.G,5),41),175)};_.bj=function Und(){return !this.G&&(this.G=DDb(this)),dF(dF(dF(this.G,5),41),175)};_.Of=function Vnd(){return dF(jEb(this),84)};_.Fg=function Wnd(){dF(jEb(this),84).c=this.v;dF(jEb(this),84).i=this.A};_.sj=function Xnd(){aSc((dF(jEb(this),84),ilb(rob(dF(jEb(this),84)))))};_.Mi=function Ynd(b){var c,d,e,f,g,i;Qnd(this);for(d=L1b(this).xe();d.Zf();){c=dF(d.$f(),7);e=dF((!this.G&&(this.G=DDb(this)),dF(dF(dF(this.G,5),41),175)).b.bn(c),2);try{gSc(dF(jEb(this),84),c.Of(),e)}catch(a){a=Xgb(a);if(!fF(a,216))throw Wgb(a)}}for(g=b.b.xe();g.Zf();){f=dF(g.$f(),7);if(f.Bg()==this){continue}i=f.Of();i.pf()&&eSc(dF(jEb(this),84),i)}};_.Hg=function Znd(a){lEb(this,a);Qnd(this);lSc(dF(jEb(this),84).j);dF(jEb(this),84).j=null};_.Ni=function $nd(a){hSc(dF(jEb(this),84),a)};_.Fh=function _nd(a,b){};_.b=false;$gb(175,41,{5:1,354:1,41:1,175:1,3:1},zEd);var Obb=UHd('com.vaadin.shared.ui.customlayout.','CustomLayoutState',175),c8=UHd('com.vaadin.client.ui.customlayout.',J6d,1402),g3=UHd(I9d,'VCustomLayout',84),B_=UHd(uae,'ConnectorBundleLoaderImpl$13$1$1',1592),C_=UHd(uae,'ConnectorBundleLoaderImpl$13$1$2',1942),D_=UHd(uae,'ConnectorBundleLoaderImpl$13$1$3',yVd);CSd($h)(13);\n//# sourceURL=gov.va.ehtac.myappsonfhir.gwt.HealthElementsForPatientWidgetSet-13.js\n")