function addFlagsToDropdown() {
    // Get the dropdown element
    var dropdown = document.getElementById("search_pays");

    // Loop through each option in the dropdown
    for (var i = 0; i < dropdown.options.length; i++) {
        var option = dropdown.options[i];
        var countryCode = option.value; // Assuming the value is the country code
        var flagUrl = "https://www.countryflags.io/" + countryCode + "/flat/de.png"; // Change the URL pattern as needed

        // Create an image element for the flag
        var flagImg = document.createElement("img");
        flagImg.src = flagUrl;
        flagImg.className = "flag";

        // Insert the flag before the text of the option
        option.text = ""; // Clear existing text
        option.insertBefore(flagImg, option.firstChild);
    }
}

// Call the function when the page loads
window.onload = addFlagsToDropdown;
