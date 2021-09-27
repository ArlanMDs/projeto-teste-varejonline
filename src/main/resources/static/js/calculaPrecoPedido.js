function calculaPrecoPedido() {
	var preco = document.getElementById('preco').value;
	var qtd = document.getElementById('qtd').value;
	var desconto = document.getElementById('desconto').value;
//	var rowstat = document.getElementById('rowStat.index').value
	
	var precoTotalComDesconto = (

		(parseFloat(preco) * parseInt(qtd))
		 -
		(parseFloat(preco) * parseInt(qtd) * (desconto / 100))

		);
	console.log(precoTotalComDesconto);
//	document.write(precoTotalComDesconto[]);
}