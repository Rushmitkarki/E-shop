document.getElementById("discountAdd").addEventListener("click", addDiscount);
document.getElementById("discountAdd").addEventListener("click", calculateTotal);

function addDiscount() {
    var discount = document.getElementById("discountInput").value;
    var discountPercent = document.getElementById("discount");
    discountPercent.textContent = discount;
}

function deleteCartItem(cartId) {
    // Make an HTTP POST request to the controller endpoint for deleting the cart entry
    fetch("/buyer/cart/delete/" + cartId, {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        }
    })
        .then(response => {
            // Handle the response from the controller
            if (response.ok) {
                // If the request was successful, remove the row from the table
                var row = document.querySelector(`tr[data-cart-id="${cartId}"]`);
                if (row) {
                    row.remove();
                    calculateSubTotal();
                    calculateTotal();
                }
            } else {
                // Handle the error if the request was not successful
                console.error("Error: " + response.status);
            }
        })
        .catch(error => {
            console.error("Error: " + error);
        });
}

var removeButtons = document.getElementsByClassName("remove-item-btn");
for (var i = 0; i < removeButtons.length; i++) {
    removeButtons[i].addEventListener("click", function () {
        var row = this.closest("tr");
        var cartId = row.getAttribute("data-cart-id"); // Get the cart ID from a data attribute or any other relevant source
        deleteCartItem(cartId);
    });
}

function calculateTotal() {
    var subTotal = parseFloat(document.getElementById("sub-total").innerText);
    var discount = parseFloat(document.getElementById("discount").innerText);
    var total = subTotal - (subTotal * discount / 100);
    document.getElementById("total").innerText = total.toFixed(2);
}

function calculateSubTotal() {
    var table = document.getElementById("cart-table");
    var rows = table.getElementsByTagName("tr");
    var subTotal = 0;

    for (var i = 1; i < rows.length; i++) {
        var priceCell = rows[i].cells[2];
        var quantityCell = rows[i].cells[3];
        var totalCell = rows[i].cells[4];

        var price = parseFloat(priceCell.innerText);
        var quantity = parseInt(quantityCell.innerText);
        var total = price * quantity;

        totalCell.innerText = total.toFixed(2);
        subTotal += total;
    }


    document.getElementById("sub-total").innerText = subTotal.toFixed(2);
}
document.addEventListener("DOMContentLoaded", function () {
    calculateSubTotal();
    calculateTotal();


});