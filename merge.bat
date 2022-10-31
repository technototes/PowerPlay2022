@echo off
echo Put every branch that you want to pull forward in the "branches.txt" file
pause
for /F "" %%D in (branches.txt) do (
  git checkout %%D
  git pull
  git merge main
  echo Ctrl-C now if you don't want to push this to github
  pause
  git push
)