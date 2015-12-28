$( function () {
    $("#entidad_fk").change( function () {

            var nt;

            nt = window.location.href;

            if (nt.match(/eid=/)) {
                window.location.href=nt.replace(/eid=\d+/,'eid=' + this.value) ;
            }
            else {
                window.location.href=nt + '&eid=' + this.value ;
            }
    });
});
