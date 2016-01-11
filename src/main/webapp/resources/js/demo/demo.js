var demo = {
		
	excel : null,
	init : function(){
		var data = [
	                ['', 'Kia', 'Nissan', 'Toyota', 'Honda'],
	                ['2008', -5, '', 12, 13],
	                ['2009', '', -11, 14, 13],
	                ['2010', '', 15, -12, 'readOnly']
	              ],
	              container;
		container = $('#js_handsontable')[0];
		
		Handsontable.renderers.registerRenderer('negativeValueRenderer', demo.negativeValueRenderer);
		
		demo.excel = new Handsontable(container, {
            data: data,
            startRows: 5,
            startCols: 5,
            minSpareRows: 1,
            contextMenu: true,
            onSelection: function (row, col, row2, col2) {
              var meta = this.getCellMeta(row2, col2);

              if (meta.readOnly) {
                this.updateSettings({fillHandle: false});
              }
              else {
                this.updateSettings({fillHandle: true});
              }
            },
            cells: function (row, col, prop) {
              var cellProperties = {};

              if (row === 0 || this.instance.getData()[row][col] === 'readOnly') {
                cellProperties.readOnly = true; // make cell read-only if it is first row or the text reads 'readOnly'
              }
              if (row === 0) {
                cellProperties.renderer = demo.firstRowRenderer; // uses function directly
              }
              else {
                cellProperties.renderer = "negativeValueRenderer"; // uses lookup map
              }

              return cellProperties;
            }
          });
	},
	
	firstRowRenderer : function(instance, td, row, col, prop, value, cellProperties){
		Handsontable.renderers.TextRenderer.apply(this, arguments);
        td.style.fontWeight = 'bold';
        td.style.color = 'green';
        td.style.background = '#CEC';
	},
	
	negativeValueRenderer : function(instance, td, row, col, prop, value, cellProperties){
		Handsontable.renderers.TextRenderer.apply(this, arguments);

        // if row contains negative number
        if (parseInt(value, 10) < 0) {
          // add class "negative"
          td.className = 'negative';
        }

        if (!value || value === '') {
          td.style.background = '#EEE';
        }
        else {
          if (value === 'Nissan') {
            td.style.fontStyle = 'italic';
          }
          td.style.background = '';
        }
	}
}

$(function(){
	demo.init();
	
	console.info(demo.excel.getData());
})