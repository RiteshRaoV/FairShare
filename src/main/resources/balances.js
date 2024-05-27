function markAsPaid(event) {
  event.preventDefault();
  const link = event.target;
  const reimbursement = link.closest('.reimbursement');
  reimbursement.style.display = 'none';
  alert('Marked as paid');
}
