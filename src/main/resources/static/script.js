$(document).ready(function() {
	var numberOfPersons = 1;
	var numberOfItems = 4;

	function createItem(personIndex, itemIndex, itemName, itemPrice) {
		var newItem = `
            <div class="item">
                <div class="inputGroup-left">
                    <label for="itemName${personIndex}-${itemIndex}">Item:</label>
                    <input type="text" class="itemName" id="itemName${personIndex}-${itemIndex}" value="${itemName}" required>
                </div>
                <div class="inputGroup-right">
                    <label for="itemPrice${personIndex}-${itemIndex}">Price:</label>
                    <input type="number" class="itemPrice" id="itemPrice${personIndex}-${itemIndex}" value="${itemPrice}" step="0.01" required>
                </div>
            </div>`;
		return newItem;
	}

	function createPerson(personIndex) {
		var newPerson = `
            <div class="personContainer">
                <div>
                    <label for="personName${personIndex}">Name:</label>
                    <input type="text" id="personName${personIndex}" value="Person ${personIndex}" required>
                </div>
                <div id="itemsContainer${personIndex}">
                    <div class="personItems">`;

		for (var i = 1; i <= numberOfItems; i++) {
			var randomPrice = (Math.random() * (39.99 - 9.99) + 9.99).toFixed(2);
			newPerson += createItem(personIndex, i, `Item ${i}`, randomPrice);
		}

		newPerson += `
                    </div>
                </div>
            </div>`;
		return newPerson;
	}

	// Adicionar nova pessoa ao clicar no botão "Add Person"
	$("#addPersonButton").on("click", function() {
		numberOfPersons++;
		var newPerson = createPerson(numberOfPersons);
		$("#formContainer").append(newPerson);
	});

	// Cria as 5 pessoas iniciais
	for (var i = 1; i <= numberOfPersons; i++) {
		var newPerson = createPerson(i);
		$("#formContainer").append(newPerson);
	}

	// Enviar o formulário para a API ao clicar em "Calculate"
	$("#calculateButton").on("click", function() {
		var fairShareData = {
			orderByPerson: [],
			shipping: parseFloat($("#shipping").val()),
			discount: parseFloat($("#discount").val())
		};

		for (var i = 1; i <= numberOfPersons; i++) {
			var personName = $(`#personName${i}`).val();
			var items = [];
			for (var j = 1; j <= numberOfItems; j++) {
				var itemName = $(`#itemName${i}-${j}`).val();
				var itemPrice = parseFloat($(`#itemPrice${i}-${j}`).val());
				items.push({ name: itemName, price: itemPrice });
			}
			fairShareData.orderByPerson.push({ personName: personName, items: items });
		}

		fetch("http://localhost:8080/api/v1/fairShareEats/calculateFairShareEatsByPerson", {
			method: "POST",
			headers: {
				"Content-Type": "application/json"
			},
			body: JSON.stringify(fairShareData)
		})
			.then(response => response.json())
			.then(data => {
				// Limpa a seção "Result" antes de preencher os resultados
				$("#resultList").empty();

				// Itera sobre os resultados e cria elementos para cada pessoa
				data.forEach(personData => {
					var personElement = `
                    <div class="resultItem">
                        <span class="resultLabel">Person Name:</span> ${personData.personName}<br>
                        <span class="resultLabel">Payment:</span> ${personData.paymentProportional.toFixed(2)}<br>
                        <span class="resultLabel">Percentage:</span> ${personData.paymentProportionalInPercentage.toFixed(2)}%<br>
                        <span class="resultLabel">QR CODE:</span><br>
                        <img class="qrCodeImage" src="${personData.qrCodePayment}" alt="QR Code">
                    </div>
                `;

					$("#resultList").append(personElement);
				});
			})
			.catch(error => {
				console.error("Erro:", error);
			});
	});
});
