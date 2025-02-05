// In-memory database for customers
const customers = {};

// Function to add a new customer
function addCustomer() {
  document.getElementById('customer-form').style.display = 'block';
}

// Function to record meter reading
function recordMeterReading() {
  const customerId = prompt("Enter Customer ID:");
  if (customers[customerId]) {
    document.getElementById('meter-reading-form').style.display = 'block';
  } else {
    alert("Customer not found.");
  }
}

// Function to generate bill
function generateBill() {
  const customerId = prompt("Enter Customer ID:");
  if (customers[customerId]) {
    const customer = customers[customerId];
    customer.generateBill();
    alert(`Bill for ${customer.name}: ${customer.bill}`);
  } else {
    alert("Customer not found.");
  }
}

// Function to view all bills
function viewAllBills() {
  const billsList = document.getElementById('bills-list');
  billsList.innerHTML = ''; // Clear previous list
  for (const customerId in customers) {
    const customer = customers[customerId];
    const listItem = document.createElement('li');
    listItem.textContent = `Customer: ${customer.name}, Bill: ${customer.bill}`;
    billsList.appendChild(listItem);
  }
  document.getElementById('bill-section').style.display = 'block';
}

// Function to submit a new customer
function submitNewCustomer() {
  const customerId = document.getElementById('customer-id').value;
  const customerName = document.getElementById('customer-name').value;
  if (customerId && customerName) {
    customers[customerId] = new Customer(customerId, customerName);
    alert("Customer added successfully!");
    cancelForm(); // Hide the form
  } else {
    alert("Please fill all the fields.");
  }
}

// Function to submit meter reading
function submitMeterReading() {
  const customerId = prompt("Enter Customer ID to update meter reading:");
  const meterReading = document.getElementById('meter-reading').value;
  if (customers[customerId] && meterReading) {
    customers[customerId].recordMeterReading(Number(meterReading));
    alert("Meter reading recorded successfully.");
    cancelForm();
  } else {
    alert("Please provide valid details.");
  }
}

// Function to cancel and hide forms
function cancelForm() {
  document.getElementById('customer-form').style.display = 'none';
  document.getElementById('meter-reading-form').style.display = 'none';
}

// Customer class with necessary methods
class Customer {
  constructor(id, name) {
    this.id = id;
    this.name = name;
    this.meterReading = 0;
    this.bill = 0;
  }

  recordMeterReading(reading) {
    this.meterReading = reading;
  }

  generateBill() {
    const unitsConsumed = this.meterReading;
    if (unitsConsumed <= 100) {
      this.bill = unitsConsumed * 5;
    } else if (unitsConsumed <= 200) {
      this.bill = (100 * 5) + ((unitsConsumed - 100) * 7);
    } else {
      this.bill = (100 * 5) + (100 * 7) + ((unitsConsumed - 200) * 10);
    }
  }
}
