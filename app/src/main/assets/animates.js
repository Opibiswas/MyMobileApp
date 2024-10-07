const tl = gsap.timeline({
    default: {duration: 0.75, ease: "Power3.easeOut"} // Set default timeline
});

// Animation for the image
tl.fromTo(".annimate-img", {
        opacity: 0, // Start with opacity 0
        scale: 0.8 // Start with a slightly smaller scale
    }, {
        opacity: 1, // End with opacity 1
        scale: 1.8, // End with original scale
        delay: 0.25, // Delay the animation
        duration: 1.5, // Duration of the animation
        ease: "power2.out" // Smooth easing
    });

// Animation for text elements
tl.fromTo(".class1, .class2, .class3, .class4, .class5, .class6", {
        opacity: 0, // Start with opacity 0
        y: 20 // Move text elements down slightly
    }, {
        opacity: 1, // End with opacity 1
        y: 0, // End with original position
        duration: 1, // Duration of the animation
        stagger: 0.2, // Add a slight delay between each text element
        ease: "power2.out" // Smooth easing
});

// Animation for the farmer image
tl.fromTo(".farmer-png", {
        opacity: 0, // Start with opacity 0
        scale: 0.8 // Start with a slightly smaller scale
    }, {
        opacity: 1, // End with opacity 1
        scale: 1.5, // End with original scale
        duration: 1, // Duration of the animation
        ease: "power2.out" // Smooth easing
});
