# SharedPrinter
<h1>Demonstrate the mechanism of a Java monitor by a shared printer</h1>
<h2>This project includes the FSP design and Java multi threaded implementation for a shared printer used by 2 students and 1 technitian.</h2>
  <b>FSP</b>
  There are 3 induvidual processes.
<ol>
  <li>Printer process</li>
  <ul>
    <li>Initialize with 3 papers</li>
    <li>Should have atleast 1 paper to proceed printing</li>
    <li>Users must have mutually exclusive access to the printer</li>
    <li>Paper count is decremented in each print</li>
  </ul>
  <li>Student Process</li>
  <ul>
    <li>Initialize with number of documets to print (1 document = 1 paper)</li>
    <li>Must take mutually exclusive access to printer to print</li>
    <li>Print document and decrement document count</li>
    <li>Release control of the printer</li>
  </ul>
  <li>Technician Process</li>
   <ul>
    <li>Repeatedly check if the printer is out of papers</li>
    <li>When printer is out of papers, fill it with maximum number of papers</li>
  </ul>
</ol>

Then the composite process will be,
<ul>
    <li>One student process that is to print 3 documents. </li>
    <li>One student process that is to print 2 documents</li>
    <li>One technician process that refills the printer with 3 sheets of paper.</li>
    <li>One printer process that can hold up to 3 sheets of paper.</li>
  </ul>

<b>In addition to the above mechanism, The java implementation of the scenario will add another user who is a technician who re-fills the toner of the printer.</b>
