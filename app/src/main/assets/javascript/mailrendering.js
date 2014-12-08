(function resizeImagesAndTables () {
	var imgs = document.getElementsByTagName('img');
	var tables = document.getElementsByTagName('table');
	for (var i = 0; i < imgs.length; i++)    
	{    
		if (imgs[i].width > %d)
		{    
			imgs[i].setAttribute('data', 'width : ' + imgs[i].width);    
			imgs[i].style.width='100%%';    
		}    
	}    
	for (var j = 0; j < tables.length; j++)    
	{
		if (tables[j].width > %d)
		{
			tables[i].setAttribute('data-width', tables[i].style.width);    
			tables[j].style.width='100%%';    
		}
	}    
})();