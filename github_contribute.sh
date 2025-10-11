#!/bin/bash

echo "🚀 GitHub Contribution Script"
echo "=============================="

# Step 1: Configure Git
echo "📋 Step 1: Configuring Git..."
echo "Please update the script with your actual GitHub username and email"
echo ""

# Step 2: Update remote
echo "📋 Step 2: Updating remote to your fork..."
git remote set-url origin https://github.com/Moeenuddin178/ProphetsStories.git

# Step 3: Push to GitHub
echo "📋 Step 3: Pushing to GitHub..."
git push origin main

echo ""
echo "✅ Push completed!"
echo ""
echo "📋 Next steps:"
echo "1. Go to: https://github.com/Moeenuddin178/ProphetsStories"
echo "2. Click 'Compare & pull request'"
echo "3. Use title: 'feat: Complete Compose Multiplatform migration with iOS support'"
echo "4. Copy content from PULL_REQUEST.md for description"
echo "5. Add: 'Closes #8, Closes #4, Addresses #9'"
echo ""
echo "🎉 Your contribution is ready!"
