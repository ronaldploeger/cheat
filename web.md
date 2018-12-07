# Critical Render Path

- https://calendar.perfplanet.com/2012/deciphering-the-critical-rendering-path/
- https://developers.google.com/web/fundamentals/performance/critical-rendering-path/

# Lifecycle Events, Navigation Timing API, Document.readyState

- The document is marked as “interactive” when the user agent stops parsing the document. Meaning, the DOM tree is ready.
- The user agent fires the DOMContentLoaded (DCL) event once any scripts marked with “defer have been executed, and there are no stylesheets that are blocking scripts. Meaning, the CSSOM is ready.

- **DOMContentLoaded** the browser fully loaded HTML, and the DOM tree is built, but external resources like pictures <img> and stylesheets may be not yet loaded
- **load** the browser loaded all resources (images, styles etc)

- https://developers.google.com/web/fundamentals/performance/critical-rendering-path/measure-crp
- https://developer.mozilla.org/en-US/docs/Web/API/Document/readyState

# Javascript

- **defer**: Download in parallel, execute in order just _before_ DOMContentLoaded
- **async**: Download in parallel, execute them as soon as possible in whatever order
