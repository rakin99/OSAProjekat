var categoriesURL = 'http://localhost:8080/api/categories';

$(document).ready(function() {
	
	// Kada se ucita stranica, pokupimo listu kategorija sa servera i popunimo tabelu
	$.ajax({
		url: categoriesURL,
		dataType: "json",
		success: function(response) {
			for(var i=0; i<response.length; i++) {
				category = response[i];
				// Za svaku kategoriju kreiramo link sa CSS klasom categoryLink
				var categoryLink = $('<a></a>');
				categoryLink.addClass('categoryLink');
				categoryLink.text(category.name);
				categoryLink.attr('title', category.description);
				categoryLink.attr('href', '/api/categories/'+category.id+'/products');
				$('#categoryList').append(categoryLink);
				
				var newOption=$('<option></option>');
				newOption.val(category.id);
				newOption.text(category.name);
				$('#cb_categories').append(newOption);
			}
		},
		error: function(request, options, error) {
			alert(error);
		}
	});
	
	$(document).on('click','.categoryLink', function(e){
		e.preventDefault();
		$("#categoryProducts").empty();
		var productsURL=$(this).attr('href');
		
		$.ajax({
			url: productsURL,
			dataType: "json",
			success:function(response){
				if(response.length==0){
					var message=$('<h3>Ne postoje proizvodi za trazenu kategoriju</h3>');
					$('#categoryProducts').append(message);
				}else{
					var newOl=$('<ol></ol>');
					for(var i=0; i<response.length; i++){
						var product=response[i];
						var newLi=$('<li></li>');
						newLi.text(product.name+', '+product.price+'$');
						newOl.append(newLi);
					}
					$('#categoryProducts').append(newOl);
				}
			},
			error: function(request, options, error){
				alert(error)
			}
		})
	});
	
	$.ajax({
		url: '/api/suppliers',
		dataType: 'json',
		success: function(response){
			for(var i=0; i<response.length; i++){
				var supplier=response[i];
				
				var newOption=$('<option></option>');
				newOption.val(supplier.id);
				newOption.text(supplier.name);
				$('#cb_suppliers').append(newOption);
			}
		},
		error: function(response, options, error){
			alert(error)
		}
	});


});